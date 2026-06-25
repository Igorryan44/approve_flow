package com.igor.approve_flow.controller;

import com.igor.approve_flow.dtos.request.ApproveRequestDto;
import com.igor.approve_flow.dtos.response.ApproveResponseDto;
import com.igor.approve_flow.model.RequestStatus;
import com.igor.approve_flow.service.ApproveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/approve")
public class ApproveController {

    private final ApproveService approveService;

    public ApproveController(ApproveService approveService) {
        this.approveService = approveService;
    }

    @PostMapping
    public ResponseEntity<ApproveResponseDto> createApprove(@Valid @RequestBody ApproveRequestDto request) {
        return ResponseEntity.ok(approveService.createApprove(request));
    }

    @GetMapping
    public ResponseEntity<List<ApproveResponseDto>> listAllApproves() {
        return ResponseEntity.ok(approveService.listAllApproves());
    }

    @GetMapping("/status")
    public ResponseEntity<List<ApproveResponseDto>> findByStatus(@RequestParam(required = true) RequestStatus status) {
        return ResponseEntity.ok(approveService.findByStatus(status));
    }

    @GetMapping("/active")
    public ResponseEntity<List<ApproveResponseDto>> listActiveApproves() {
        return ResponseEntity.ok(approveService.listActiveApproves());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApproveResponseDto> findApproveById(@PathVariable Long id) {
        return ResponseEntity.ok(approveService.findApproveById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelApproveById(@PathVariable Long id) {
        approveService.cancelApproveById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
