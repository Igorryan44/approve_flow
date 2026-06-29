package com.igor.approve_flow.Handler;

import com.igor.approve_flow.Exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessage> entityNotFoundException(){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND.value(), "User not found", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(restErrorMessage);
    }

    @ExceptionHandler(UserAlreadyException.class)
    private ResponseEntity<RestErrorMessage> userAlreadyException(UserAlreadyException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(restErrorMessage);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    private ResponseEntity<RestErrorMessage> incorrectPasswordException(IncorrectPasswordException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(restErrorMessage);
    }

    @ExceptionHandler(ApproveNotFoundException.class)
    private ResponseEntity<RestErrorMessage> approveNotFoundException(ApproveNotFoundException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(restErrorMessage);
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<RestErrorMessage> accessDeniedException(AccessDeniedException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.FORBIDDEN.value(), ex.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(restErrorMessage);
    }

    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<RestErrorMessage> authenticationException(AuthenticationException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(restErrorMessage);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    private ResponseEntity<RestErrorMessage> invalidArgumentException(InvalidArgumentException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(restErrorMessage);
    }

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<RestErrorMessage> invalidTokenException(InvalidTokenException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(restErrorMessage);
    }
}
