package com.benyovszki.szakdolgozat.security.filter;


import com.benyovszki.szakdolgozat.util.JwtFilterResponseUtil;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@AllArgsConstructor
public class SecurityFilterExceptionHandlerFilter extends OncePerRequestFilter {

    private JwtFilterResponseUtil jwtFilterResponseUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (JwtException j) {
            jwtFilterResponseUtil.setErrorWithException(response, HttpStatus.UNAUTHORIZED, j);
        }
    }
}
