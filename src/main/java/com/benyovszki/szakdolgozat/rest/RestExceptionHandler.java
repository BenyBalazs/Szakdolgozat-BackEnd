package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.response.ErrorResponse;
import com.benyovszki.szakdolgozat.dto.response.RestErrorResponse;
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

        RestErrorResponse errorResponse = new RestErrorResponse();
        errorResponse.setTimeStamp(DateTime.now().toString());
        errorResponse.setErrorCode(status.value());
        errorResponse.setErrorType(operationException.getErrorType());
        errorResponse.setException(operationException.getClass().getName());
        errorResponse.setMassage(status.getReasonPhrase());
        errorResponse.setServicePath(request.getRequestURI());
        errorResponse.setServiceName(handlerMethod.getMethod().getName());
        errorResponse.setExceptionMassage(operationException.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(OperationException operationException, HandlerMethod handlerMethod, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        RestErrorResponse errorResponse = new RestErrorResponse();
        errorResponse.setTimeStamp(DateTime.now().toString());
        errorResponse.setErrorCode(status.value());
        errorResponse.setErrorType(operationException.getErrorType());
        errorResponse.setException(operationException.getClass().getName());
        errorResponse.setMassage(status.getReasonPhrase());
        errorResponse.setServicePath(request.getRequestURI());
        errorResponse.setServiceName(handlerMethod.getMethod().getName());
        errorResponse.setExceptionMassage(operationException.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(AccessDeniedException accessDeniedException, HandlerMethod handlerMethod, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        RestErrorResponse errorResponse = new RestErrorResponse();
        errorResponse.setTimeStamp(DateTime.now().toString());
        errorResponse.setErrorCode(status.value());
        errorResponse.setErrorType(ErrorType.ACCESS_DENIED);
        errorResponse.setException(accessDeniedException.getClass().getName());
        errorResponse.setMassage(status.getReasonPhrase());
        errorResponse.setServicePath(request.getRequestURI());
        errorResponse.setServiceName(handlerMethod.getMethod().getName());
        errorResponse.setExceptionMassage(accessDeniedException.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }
}
