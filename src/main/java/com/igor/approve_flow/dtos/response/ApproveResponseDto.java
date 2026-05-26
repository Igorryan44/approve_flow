package com.igor.approve_flow.dtos.response;

import com.igor.approve_flow.model.RequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ApproveResponseDto(
        Long id,
        String request_name,
        Long user_id,
        List<String> assignees,
        @Enumerated(EnumType.STRING)
        RequestStatus status,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime created_at,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime last_update
) {
}
