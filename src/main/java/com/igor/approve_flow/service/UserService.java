package com.igor.approve_flow.service;

import com.igor.approve_flow.Exceptions.UserAlreadyException;
import com.igor.approve_flow.dtos.request.RegisterUserRequest;
import com.igor.approve_flow.dtos.request.UpdatePasswordDto;
import com.igor.approve_flow.dtos.request.UserRequestDto;
import com.igor.approve_flow.dtos.response.RegisterUserResponse;
import com.igor.approve_flow.dtos.response.UserResponseDto;
import com.igor.approve_flow.mapper.UserMapper;
import com.igor.approve_flow.model.User;
import com.igor.approve_flow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserResponseDto> listUsers() {
        return mapper.toDto(userRepository.findAll());
    }

    public UserResponseDto findById(Long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public UserResponseDto findByEmail(String email) {
        return mapper.toDto(userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new));
    }

    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } throw new EntityNotFoundException();
    }

    public void updatePassword(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setPassword(passwordEncoder.encode(password));

        mapper.toDto(userRepository.save(user));
    }

}
