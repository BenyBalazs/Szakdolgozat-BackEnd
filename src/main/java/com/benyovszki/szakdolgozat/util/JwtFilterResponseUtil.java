package com.benyovszki.szakdolgozat.util;

import com.benyovszki.szakdolgozat.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class JwtFilterResponseUtil {

    public void setErrorWithException(HttpServletResponse response, int errorCore, String msg, Exception ex) throws IOException {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCore);
        errorResponse.setMassage(msg);
        errorResponse.setException(ex.getMessage());

        byte[] responseToSend = restResponseBytes(errorResponse);
        ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
        ((HttpServletResponse) response).setStatus(errorCore);
        response.getOutputStream().write(responseToSend);
    }

    private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }
}
