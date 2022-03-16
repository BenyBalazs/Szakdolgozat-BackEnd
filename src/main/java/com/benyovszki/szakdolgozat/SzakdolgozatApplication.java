package com.benyovszki.szakdolgozat;

import com.benyovszki.szakdolgozat.model.Category;
import dto.szakdolgozat.benyovszki.com.category.CategoryEntityType;
import dto.szakdolgozat.benyovszki.com.transaction.*;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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

		mapper.addMappings(new PropertyMap<TransactionEntityType, Transaction>() {
			@Override
			protected void configure() {
				skip(destination.getDateOfAdd());
				skip(destination.getId());
				skip(destination.getOwner());
				skip(destination.getCategory());
			}
		});

		mapper.addMappings(new PropertyMap<CategoryEntityType, Category>() {
			@Override
			protected void configure() {
				skip(destination.getId());
			}
		});

		return mapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(SzakdolgozatApplication.class, args);
	}

}
