package com.benyovszki.szakdolgozat.exception;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Getter
public class OperationException extends RuntimeException {

    private ErrorType errorType;

    public OperationException(ErrorType errorType, String msg) {
        super(msg);
        if(Objects.nonNull(errorType)) {
            this.errorType = errorType;
        }
    }
}
