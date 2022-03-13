package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.response.error.ErrorResponse;
import com.benyovszki.szakdolgozat.dto.response.error.RestErrorResponse;
import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.InvalidCredentialsException;
import com.benyovszki.szakdolgozat.exception.OperationException;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(InvalidCredentialsException operationException, HandlerMethod handlerMethod, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(buildResponse(operationException, status, handlerMethod, request));
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(OperationException operationException, HandlerMethod handlerMethod, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(buildResponse(operationException, status, handlerMethod, request));
    }

    private <T extends OperationException> ErrorResponse buildResponse(T ex, HttpStatus status , HandlerMethod handlerMethod, HttpServletRequest request) {
        RestErrorResponse errorResponse = new RestErrorResponse();
        errorResponse.setTimeStamp(DateTime.now().toString());
        errorResponse.setErrorCode(status.value());
        errorResponse.setErrorType(ex.getErrorType());
        errorResponse.setException(ex.getClass().getName());
        errorResponse.setMassage(status.getReasonPhrase());
        errorResponse.setServicePath(request.getRequestURI());
        errorResponse.setServiceName(handlerMethod.getMethod().getName());
        errorResponse.setExceptionMassage(ex.getMessage());
        return errorResponse;
    }
}
