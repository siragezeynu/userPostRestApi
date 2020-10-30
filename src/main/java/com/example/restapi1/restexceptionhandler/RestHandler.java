package com.example.restapi1.restexceptionhandler;

import com.example.restapi1.exception.UserNotFoundException;
import com.example.restapi1.exception.UserNotFoundExceptionDetails;
import com.example.restapi1.exception.UserNotValidatedDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@SuppressWarnings({"Unchecked"})
@Slf4j
public class RestHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundExceptionDetails>HandleResourceNotFoundException(UserNotFoundException e){
        return new ResponseEntity<>(
                UserNotFoundExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Resource Not Found")
                        .detail(e.getMessage())
                        .developerMessage(e.getClass().getName())
                        .build(),HttpStatus.NOT_FOUND);

    }

    // create an other listener
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserNotValidatedDetails>handlerUserNotFoundException(MethodArgumentNotValidException e){
        List<FieldError> fielderrors=e.getBindingResult().getFieldErrors();
        String fields=fielderrors.stream().map(FieldError::getField).collect(Collectors.joining(""));
        String fieldsMessage=fielderrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(""));
        return new ResponseEntity<>(
                UserNotValidatedDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field Validation Error ")
                .detail("Check the fild(s) below")
                .developerMessage(e.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build(),HttpStatus.BAD_REQUEST);
    }
}
