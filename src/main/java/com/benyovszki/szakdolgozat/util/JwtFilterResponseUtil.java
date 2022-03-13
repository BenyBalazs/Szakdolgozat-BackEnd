package com.benyovszki.szakdolgozat.util;

import com.benyovszki.szakdolgozat.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.time.DateUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@Component
@AllArgsConstructor
public class JwtFilterResponseUtil {

    @SneakyThrows
    public void setErrorWithException(HttpServletResponse response, HttpStatus status, Exception ex) throws IOException {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimeStamp(DateTime.now().toString());
        errorResponse.setErrorCode(status.value());
        errorResponse.setMassage(status.getReasonPhrase());
        errorResponse.setException(ex.getClass().getName());
        errorResponse.setExceptionMassage(ex.getMessage());

        byte[] responseToSend = restResponseBytes(errorResponse);
        ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
        ((HttpServletResponse) response).setStatus(status.value());
        response.getOutputStream().write(responseToSend);
    }

    private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }
}
