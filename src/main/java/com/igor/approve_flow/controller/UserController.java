package com.igor.approve_flow.controller;

import com.igor.approve_flow.dtos.request.UpdatePasswordDto;
import com.igor.approve_flow.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdatePasswordDto> updatePassword(@PathVariable Long id, String password, String new_password) {
        return ResponseEntity.ok(userService.updatePassword(id, password, new_password));
    }
}
