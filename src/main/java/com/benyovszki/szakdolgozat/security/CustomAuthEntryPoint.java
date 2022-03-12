package com.benyovszki.szakdolgozat.security;

import com.benyovszki.szakdolgozat.util.JwtFilterResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    private JwtFilterResponseUtil jwtFilterResponseUtil;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        jwtFilterResponseUtil.setErrorWithException(response, 403, "NO_PERMISSION", authException);
    }
}
