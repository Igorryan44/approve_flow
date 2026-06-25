package com.igor.approve_flow.service;

import com.igor.approve_flow.Exceptions.InvalidArgumentException;
import com.igor.approve_flow.Exceptions.UserAlreadyException;
import com.igor.approve_flow.config.TokenConfig;
import com.igor.approve_flow.dtos.request.LoginRequestDto;
import com.igor.approve_flow.dtos.request.RegisterUserRequestDto;
import com.igor.approve_flow.dtos.response.LoginResponseDto;
import com.igor.approve_flow.dtos.response.RegisterUserResponseDto;
import com.igor.approve_flow.mapper.UserMapper;
import com.igor.approve_flow.model.User;
import com.igor.approve_flow.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.igor.approve_flow.utils.AppConstants.BRAZIL_ZONE;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthService(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    public RegisterUserResponseDto register(RegisterUserRequestDto request) {

        userRepository.findByEmail(request.email())
                .ifPresent(u -> {
                    throw new UserAlreadyException();
                });

        User user =  new User();

        var pass = passwordEncoder.encode(request.password());

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(pass);
        user.setCreated_at(LocalDateTime.now(BRAZIL_ZONE));
        user.setLast_update(LocalDateTime.now(BRAZIL_ZONE));

        return mapper.toRegisterDto(userRepository.save(user));
    }

    public LoginResponseDto login(LoginRequestDto request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authentication = authenticationManager.authenticate(userAndPass);

            User user = (User) authentication.getPrincipal();
            String token = tokenConfig.generateToken(user);

            return new LoginResponseDto(token);

        } catch (Exception e) {
            throw new InvalidArgumentException();
        }

    }
}
