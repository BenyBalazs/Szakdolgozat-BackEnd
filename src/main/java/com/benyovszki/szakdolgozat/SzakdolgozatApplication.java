package com.benyovszki.szakdolgozat;

import javax.annotation.PostConstruct;

import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.model.TransactionType;
import com.benyovszki.szakdolgozat.model.user.Role;
import com.benyovszki.szakdolgozat.model.user.User;
import com.benyovszki.szakdolgozat.repository.CategoryRepository;
import com.benyovszki.szakdolgozat.repository.UserRepository;
import dto.szakdolgozat.benyovszki.com.category.CategoryEntityType;
import dto.szakdolgozat.benyovszki.com.transaction.*;
import com.benyovszki.szakdolgozat.model.Transaction;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@AllArgsConstructor
public class SzakdolgozatApplication {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private PasswordEncoder passwordEncoder;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(Transaction.class, TransactionEntityType.class).addMappings(mapping -> {
            mapping.map(source -> source.getOwner().getUsername(), TransactionEntityType::setOwner);
            mapping.map(source -> source.getCategory().getId(), TransactionEntityType::setCategoryId);
            mapping.map(source -> source.getCategory().getName(), TransactionEntityType::setCategoryName);
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

        mapper.typeMap(Category.class, CategoryEntityType.class).addMappings(mapping -> {
            mapping.map(source -> source.getOwner().getUsername(), CategoryEntityType::setOwner);
        });

        mapper.addMappings(new PropertyMap<CategoryEntityType, Category>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getOwner());
            }
        });

        return mapper;
    }

    @PostConstruct
    public void createBasicData() {
        try {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@admin.hu")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ROLE_ADMIN)
                    .build();
            userRepository.save(admin);
            categoryRepository.save(Category.builder().name("Fizet??s").transactionType(TransactionType.INCOME).build());
            categoryRepository.save(Category.builder().name("Bev??s??rl??s").transactionType(TransactionType.EXPENSE).build());
            categoryRepository.save(Category.builder().name("Aj??nd??k").transactionType(TransactionType.INCOME).build());
            categoryRepository.save(Category.builder().name("Egy??b bev??tel").transactionType(TransactionType.INCOME).build());
            categoryRepository.save(Category.builder().name("Otthon").transactionType(TransactionType.EXPENSE).build());
            categoryRepository.save(Category.builder().name("??tterem").transactionType(TransactionType.EXPENSE).build());
            categoryRepository.save(Category.builder().name("Csal??d").transactionType(TransactionType.EXPENSE).build());
            categoryRepository.save(Category.builder().name("Mag??n").transactionType(TransactionType.EXPENSE).build());
            categoryRepository.save(Category.builder().name("??lt??zk??d??s").transactionType(TransactionType.EXPENSE).build());
            categoryRepository.save(Category.builder().name("Sz??rakoz??s").transactionType(TransactionType.EXPENSE).build());
        } catch (Exception e) {
            System.out.println("Data already added");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SzakdolgozatApplication.class, args);
    }

}
