package com.igor.approve_flow.dtos.request;

import java.util.List;

public record ApproveRequestDto(
        String request_name,
        Long user_id,
        List<String> assignees
) {
}
