package com.benyovszki.szakdolgozat;

import com.benyovszki.szakdolgozat.dto.TransactionEntityType;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SzakdolgozatApplication {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		mapper.typeMap(Transaction.class, TransactionEntityType.class).addMappings(mapping -> {
			mapping.map(source -> source.getOwner().getUsername(), TransactionEntityType::setOwner);
		});

		return mapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(SzakdolgozatApplication.class, args);
	}

}
