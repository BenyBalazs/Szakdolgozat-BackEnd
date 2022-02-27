package com.benyovszki.szakdolgozat.security.jwt;

import com.benyovszki.szakdolgozat.rest.RestPaths;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        String requestUser = request.getHeader("username");

        if (!StringUtils.hasText(jwt)) {
            throw new BadCredentialsException("No token received.");
        }

        if (!"SzakdolgozatBackend".equals(jwtUtils.extractIssuer(jwt))) {
            throw new BadCredentialsException("Invalid Token received!");
        }
        String username = jwtUtils.extractUsername(jwt);

        if (!StringUtils.hasText(requestUser) || !requestUser.equals(username)) {
            throw new BadCredentialsException("Invalid Token received!");
        }

        if (jwtUtils.isTokenExpired(jwt)) {
            throw new BadCredentialsException("Invalid Token received!");
        }

        try {
            String authorities = jwtUtils.extractAuthority(jwt);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                    AuthorityUtils.createAuthorityList(authorities));
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    } catch (Exception e) {
        throw new BadCredentialsException("Invalid Token received!");
    }
        filterChain.doFilter(request,response);
}

}
