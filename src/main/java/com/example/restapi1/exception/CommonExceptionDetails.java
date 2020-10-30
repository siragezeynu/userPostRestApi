package com.example.restapi1.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@SuperBuilder
public class CommonExceptionDetails {
    protected String title;
    protected int status;
    protected String detail;
    protected LocalDateTime timestamp;
    protected String developerMessage;
}
