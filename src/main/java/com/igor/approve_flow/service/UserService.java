package com.igor.approve_flow.service;

import com.igor.approve_flow.dtos.request.RegisterUserRequest;
import com.igor.approve_flow.dtos.response.RegisterUserResponse;
import com.igor.approve_flow.mapper.UserMapper;
import com.igor.approve_flow.model.User;
import com.igor.approve_flow.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RegisterUserResponse createUser(RegisterUserRequest request) {
        User user =  new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password()); // Por enquanto, depois vira Bycript

        return mapper.toRegisterDto(repository.save(user));
    }
}
