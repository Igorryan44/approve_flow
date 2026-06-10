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
import java.util.List;

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

    public RegisterUserResponseDto register(RegisterUserRequestDto request) {
        userRepository.findByEmail(request.email()).orElseThrow(UserAlreadyException::new);
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

        var passwordEncoded = passwordEncoder.encode(password);
        var passwordDb = user.getPassword();

        if (passwordEncoder.matches(passwordDb, passwordEncoded)) {

            var newPassword = passwordEncoder.encode(new_password);
            user.setPassword(newPassword);
            userRepository.save(user);

            return new UpdatePasswordDto(passwordDb, newPassword);
        }
        throw new IncorrectPasswordException();
    }

}
