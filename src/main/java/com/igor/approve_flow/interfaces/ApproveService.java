package com.igor.approve_flow.interfaces;

import com.igor.approve_flow.dtos.request.ApproveRequestDto;
import com.igor.approve_flow.dtos.response.ApproveResponseDto;
import com.igor.approve_flow.model.RequestStatus;

import java.util.List;

public interface ApproveService {
    ApproveResponseDto createApprove(ApproveRequestDto request);
    List<ApproveResponseDto> listAllApproves();
    List<ApproveResponseDto> listActiveApproves();
    List<ApproveResponseDto> findByStatus(RequestStatus status);
    ApproveResponseDto findApproveById(Long id);
    ApproveResponseDto completeApprove(Long id);
    void cancelApproveById(Long id);
}
