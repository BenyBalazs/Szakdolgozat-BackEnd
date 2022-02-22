package com.benyovszki.szakdolgozat.security.configuration;

import java.util.Collections;
import java.util.List;

import com.benyovszki.szakdolgozat.security.authentication.filters.AuthoritiesLoggingAfterFilter;
import com.benyovszki.szakdolgozat.security.authentication.filters.AuthoritiesLoggingAtFilter;
import com.benyovszki.szakdolgozat.security.authentication.filters.JWTTokenGeneratorFilter;
import com.benyovszki.szakdolgozat.security.authentication.filters.JWTTokenValidatorFilter;
import com.benyovszki.szakdolgozat.security.authentication.filters.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                        cors().configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setExposedHeaders(List.of("Authorization"));
                            config.setMaxAge(3600L);
                            return config;
                        }).and().csrf().disable()
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers("/myAccount").hasRole("USER")
                //.antMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                //.antMatchers("/myLoans").hasRole("ROOT")
               // .antMatchers("/myCards").hasAnyRole("USER","ADMIN")
               // .antMatchers("/user").authenticated()
               // .antMatchers("/notices").permitAll()
               // .antMatchers("/contact").permitAll()
                .and().httpBasic().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
