package com.igor.approve_flow.Handler;

import com.igor.approve_flow.Exceptions.ApproveNotFoundException;
import com.igor.approve_flow.Exceptions.IncorrectPasswordException;
import com.igor.approve_flow.Exceptions.UserAlreadyException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessage> entityNotFoundException(){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, "User not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler(UserAlreadyException.class)
    private ResponseEntity<RestErrorMessage> userAlreadyException(UserAlreadyException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restErrorMessage);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    private ResponseEntity<RestErrorMessage> incorrectPasswordException(IncorrectPasswordException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(ApproveNotFoundException.class)
    private ResponseEntity<RestErrorMessage> approveNotFoundException(ApproveNotFoundException ex){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }
}
