package com.benyovszki.szakdolgozat.security;

import com.benyovszki.szakdolgozat.util.JwtFilterResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAccessDeniedHAndler implements AccessDeniedHandler {

    private JwtFilterResponseUtil jwtFilterResponseUtil;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        jwtFilterResponseUtil.setErrorWithException(response, 403, "ACCESS_DENIED_HANDLER", accessDeniedException);
    }
}
