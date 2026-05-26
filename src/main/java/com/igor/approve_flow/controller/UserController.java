package com.igor.approve_flow.controller;

import com.igor.approve_flow.dtos.request.UpdatePasswordDto;
import com.igor.approve_flow.dtos.response.UserResponseDto;
import com.igor.approve_flow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> listAllUsers() {
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
