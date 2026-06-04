package com.igor.approve_flow.service;

import com.igor.approve_flow.Exceptions.ApproveNotFoundException;
import com.igor.approve_flow.dtos.request.ApproveRequestDto;
import com.igor.approve_flow.dtos.response.ApproveResponseDto;
import com.igor.approve_flow.mapper.ApproveMapper;
import com.igor.approve_flow.model.ApproveRequest;
import com.igor.approve_flow.model.RequestStatus;
import com.igor.approve_flow.model.User;
import com.igor.approve_flow.repository.ApproveRequestRepository;
import com.igor.approve_flow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ApproveService {

    private final ApproveRequestRepository approveRequestRepository;
    private final UserRepository userRepository;
    private final ApproveMapper approveMapper;

    public ApproveService(ApproveRequestRepository approveRequestRepository, ApproveMapper approveMapper, UserRepository userRepository) {
        this.approveRequestRepository = approveRequestRepository;
        this.approveMapper = approveMapper;
        this.userRepository = userRepository;
    }

    public ApproveResponseDto createApprove(ApproveRequestDto request) {
        ApproveRequest newApprove = new  ApproveRequest();

        User user = userRepository.findById(request.user_id()).orElseThrow(EntityNotFoundException::new);

        Set<User> assignessList = request.assignees().stream()
                .filter(Objects::nonNull)
                .map(id -> userRepository.findById(id).orElseThrow(EntityNotFoundException::new))
                .collect(Collectors.toSet());

        newApprove.setRequest_name(request.request_name());
        newApprove.setUser(user);
        newApprove.setAssignees(assignessList);
        newApprove.setStatus(RequestStatus.REQUESTED);
        newApprove.setCreated_at(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        newApprove.setLast_update(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        return approveMapper.toDto(approveRequestRepository.save(newApprove));
    }

    public List<ApproveResponseDto> listAllApproves() {
        return approveMapper.toDto(approveRequestRepository.findAll());
    }

    public List<ApproveResponseDto> findByStatus(RequestStatus status) {
        List<ApproveRequest> requests = approveRequestRepository.findByStatus(status);
        if (requests.isEmpty()) {
            throw new ApproveNotFoundException();
        }
        return approveMapper.toDto(requests);
    }

    public ApproveResponseDto findApproveById(Long id) {
        return approveMapper.toDto(approveRequestRepository.findById(id).orElseThrow(ApproveNotFoundException::new));
    }

    public ApproveResponseDto completeApprove(Long id) {
        ApproveRequest approve = approveRequestRepository.findById(id).orElseThrow(ApproveNotFoundException::new);
        approve.setStatus(RequestStatus.COMPLETED);
        approve.setLast_update(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        approveRequestRepository.save(approve);
        return approveMapper.toDto(approve);
    }

    public void cancelApproveById(Long id) {
        ApproveRequest approve =  approveRequestRepository.findById(id).orElseThrow(ApproveNotFoundException::new);
        approve.setStatus(RequestStatus.CANCELED);
        approve.setLast_update(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        approveRequestRepository.save(approve);
    }

}
