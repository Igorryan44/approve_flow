package com.igor.approve_flow.service;

import com.igor.approve_flow.Exceptions.ApproveNotFoundException;
import com.igor.approve_flow.dtos.request.ApproveRequestDto;
import com.igor.approve_flow.dtos.response.ApproveResponseDto;
import com.igor.approve_flow.mapper.ApproveMapper;
import com.igor.approve_flow.model.ApproveRequest;
import com.igor.approve_flow.model.RequestStatus;
import com.igor.approve_flow.repository.ApproveRequestRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class ApproveService {

    private final ApproveRequestRepository approveRequestRepository;
    private final ApproveMapper approveMapper;

    public ApproveService(ApproveRequestRepository approveRequestRepository, ApproveMapper approveMapper) {
        this.approveRequestRepository = approveRequestRepository;
        this.approveMapper = approveMapper;
    }

    private LocalDateTime dateTiMeNow() {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    public ApproveResponseDto createApprove(ApproveRequestDto request) {
        ApproveRequest newApprove = new  ApproveRequest();

        newApprove.setRequestName(request.requestName());
        newApprove.setCreated_at(dateTiMeNow());
        newApprove.setLast_update(dateTiMeNow());

        return approveMapper.toDto(approveRequestRepository.save(newApprove));
    }

    public List<ApproveResponseDto> listAllApproves() {
        return approveMapper.toDto(approveRequestRepository.findAll());
    }

    public List<ApproveResponseDto> listByStatus(RequestStatus status) {
        return approveMapper.toDto(approveRequestRepository.listByStatus(status).orElseThrow(ApproveNotFoundException::new));
    }

    public ApproveResponseDto findApproveById(Long id) {
        return approveMapper.toDto(approveRequestRepository.findById(id).orElseThrow(ApproveNotFoundException::new));
    }

    public ApproveResponseDto completeApprove(Long id) {
        var approve = approveRequestRepository.findById(id).orElseThrow(ApproveNotFoundException::new);
        approve.setStatus(RequestStatus.COMPLETED);
        approve.setLast_update(dateTiMeNow());
        approveRequestRepository.save(approve);
        return approveMapper.toDto(approve);
    }

    public void cancelApproveById(Long id) {
        var approve =  approveRequestRepository.findById(id).orElseThrow(ApproveNotFoundException::new);
        approve.setStatus(RequestStatus.CANCELED);
        approve.setLast_update(dateTiMeNow());
        approveRequestRepository.save(approve);

        approveRequestRepository.deleteById(id);
    }

}
