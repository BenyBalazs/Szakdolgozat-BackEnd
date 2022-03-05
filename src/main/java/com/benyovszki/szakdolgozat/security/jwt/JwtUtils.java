package com.benyovszki.szakdolgozat.security.jwt;

import com.benyovszki.szakdolgozat.model.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtils {

    public static final String SECRET_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String ISSUER = "SzakdolgozatBackend";
    public static final String USERNAME_CLAIM = "username";
    public static final String AUTHORITY_CLAIM = "authority";

    private final Key key;

    public JwtUtils() {
        key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        return String.valueOf(extractAllClaims(token).get(USERNAME_CLAIM));
    }

    public String extractAuthority(String token) {
        return removeFirstAndLastChar(String.valueOf(extractAllClaims(token).get(AUTHORITY_CLAIM)));
    }

    public String extractIssuer(String token) {
        return extractClaim(token, Claims::getIssuer);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token) &&
                didIIssuedThis(token)) &&
                doesAuthoritiesMatch(token, userDetails);
    }

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setIssuer(ISSUER)
                .claim(USERNAME_CLAIM, userDetails.getUsername())
                .claim(AUTHORITY_CLAIM, userDetails.getAuthorities())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key)
                .compact();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

    }

    private Boolean didIIssuedThis(String token) {
        return  ISSUER.equals(extractIssuer(token));
    }

    private Boolean doesAuthoritiesMatch(String token, UserDetails userDetails) {
        GrantedAuthority userAuthority = new ArrayList<>(userDetails.getAuthorities()).get(0);
        return userAuthority.equals(Role.valueOf(extractAuthority(token)));

    }

    public String removeFirstAndLastChar (String str) {

        // Removing first and last character
        // of a string using substring() method
        str = str.substring(1, str.length() - 1);

        // Return the modified string
        return str;
    }

}
