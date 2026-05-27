package com.igor.approve_flow.mapper;

import com.igor.approve_flow.dtos.request.ApproveRequestDto;
import com.igor.approve_flow.dtos.response.ApproveResponseDto;
import com.igor.approve_flow.model.ApproveRequest;
import com.igor.approve_flow.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApproveMapper {

    @Mapping(source = "user.id", target = "user_id")
    ApproveResponseDto toDto(ApproveRequest approve);

    ApproveRequest toEntity(ApproveRequestDto dto);

    List<ApproveResponseDto> toDto (List<ApproveRequest> approves);

    default User longToUser(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);

        return user;
    }

    default Long userToLong(User user) {
        if (user == null) {
            return null;
        }
        return user.getId();
    }
}