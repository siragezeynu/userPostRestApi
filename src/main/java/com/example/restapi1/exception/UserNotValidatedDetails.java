package com.example.restapi1.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserNotValidatedDetails extends CommonExceptionDetails {
    private String fields;
    private String fieldsMessage;
}
