package com.igor.approve_flow.service;

import com.igor.approve_flow.Exceptions.UserAlreadyException;
import com.igor.approve_flow.dtos.request.RegisterUserRequest;
import com.igor.approve_flow.dtos.response.RegisterUserResponse;
import com.igor.approve_flow.mapper.UserMapper;
import com.igor.approve_flow.model.User;
import com.igor.approve_flow.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserResponse createUser(RegisterUserRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()){
            throw new UserAlreadyException();
        }

        User user =  new User();

        var pass = passwordEncoder.encode(request.password());

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(pass);

        return mapper.toRegisterDto(userRepository.save(user));
    }
}
