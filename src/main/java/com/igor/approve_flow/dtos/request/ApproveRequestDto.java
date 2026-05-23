package com.igor.approve_flow.dtos.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record ApproveRequestDto(
        String requestName,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime created_at,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime last_update

) {
}
