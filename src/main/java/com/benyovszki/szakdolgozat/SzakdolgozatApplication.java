package com.benyovszki.szakdolgozat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class SzakdolgozatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SzakdolgozatApplication.class, args);
	}

}
