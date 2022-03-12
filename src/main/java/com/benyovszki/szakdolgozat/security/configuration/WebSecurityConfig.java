package com.benyovszki.szakdolgozat.security.configuration;

import com.benyovszki.szakdolgozat.model.user.Role;
import com.benyovszki.szakdolgozat.rest.RestPaths;
import com.benyovszki.szakdolgozat.security.CustomAccessDeniedHAndler;
import com.benyovszki.szakdolgozat.security.CustomAuthEntryPoint;
import com.benyovszki.szakdolgozat.security.filter.SecurityFilterExceptionHandlerFilter;
import com.benyovszki.szakdolgozat.security.filter.jwt.JwtFilter;
import com.benyovszki.szakdolgozat.service.impl.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomAuthEntryPoint customAuthEntryPoint;
    private JwtFilter jwtFilter;
    private CustomUserDetailsService userDetailsService;
    private SecurityFilterExceptionHandlerFilter securityFilterExceptionHandlerFilter;
    private CustomAccessDeniedHAndler accessDeniedHAndler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token", "username"));
        configuration.setExposedHeaders(List.of("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(RestPaths.BASIC_USER_PATH + "/**").permitAll()
                .antMatchers("/test/token").hasAnyRole(Role.ROLE_USER.getAuthority(), Role.ROLE_ADMIN.getAuthority())
                .antMatchers("/test/admin").hasAnyAuthority(Role.ROLE_ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(customAuthEntryPoint).accessDeniedHandler(accessDeniedHAndler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(securityFilterExceptionHandlerFilter, SecurityContextPersistenceFilter.class );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}