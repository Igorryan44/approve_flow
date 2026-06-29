package com.igor.approve_flow.service;

import com.igor.approve_flow.Exceptions.IncorrectPasswordException;
import com.igor.approve_flow.dtos.request.UpdatePasswordDto;
import com.igor.approve_flow.dtos.response.UserResponseDto;
import com.igor.approve_flow.interfaces.UserService;
import com.igor.approve_flow.mapper.UserMapper;
import com.igor.approve_flow.model.User;
import com.igor.approve_flow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserResponseDto> listAll() {
        return mapper.toDto(userRepository.findAll());
    }

    @Override
    public UserResponseDto findById(Long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return mapper.toDto(userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(id);
    }

    @Override
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
