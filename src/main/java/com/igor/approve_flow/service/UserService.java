package com.igor.approve_flow.service;

import com.igor.approve_flow.Exceptions.IncorrectPasswordException;
import com.igor.approve_flow.Exceptions.InvalidArgumentException;
import com.igor.approve_flow.Exceptions.UserAlreadyException;
import com.igor.approve_flow.config.TokenConfig;
import com.igor.approve_flow.dtos.request.LoginRequestDto;
import com.igor.approve_flow.dtos.request.RegisterUserRequestDto;
import com.igor.approve_flow.dtos.request.UpdatePasswordDto;
import com.igor.approve_flow.dtos.response.LoginResponseDto;
import com.igor.approve_flow.dtos.response.RegisterUserResponseDto;
import com.igor.approve_flow.dtos.response.UserResponseDto;
import com.igor.approve_flow.mapper.UserMapper;
import com.igor.approve_flow.model.User;
import com.igor.approve_flow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.igor.approve_flow.utils.AppConstants.BRAZIL_ZONE;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;


    public UserService(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    public List<UserResponseDto> listAll() {
        return mapper.toDto(userRepository.findAll());
    }

    public UserResponseDto findById(Long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public UserResponseDto findByEmail(String email) {
        return mapper.toDto(userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new));
    }

    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(id);
    }

    public UpdatePasswordDto updatePassword(Long id, String password, String new_password) {

        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        var passwordDb = user.getPassword();

        if (passwordEncoder.matches(password, passwordDb)) {

            var newPassword = passwordEncoder.encode(new_password);
            user.setPassword(newPassword);
            userRepository.save(user);

            return new UpdatePasswordDto(passwordDb, newPassword);
        }
        throw new IncorrectPasswordException();
    }

}
