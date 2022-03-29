package com.benyovszki.szakdolgozat.action.transaction;

import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.repository.CategoryRepository;
import dto.szakdolgozat.benyovszki.com.transaction.*;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.repository.UserRepository;
import com.benyovszki.szakdolgozat.service.impl.TransactionService;
import com.benyovszki.szakdolgozat.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Destination;

@Service
@AllArgsConstructor
public class TransactionCreateAction {

    private TransactionService transactionService;
    private CategoryRepository categoryRepository;
    private UserService userService;
    private ModelMapper mapper;

    @Transactional
    public TransactionResponse createTransaction(TransactionCreateRequest createRequest) {
        Transaction transaction = mapper.map(createRequest.getTransactionData(), Transaction.class);
        transaction.setOwner(userService.getByUsername(createRequest.getOwner()));
        transaction.setDateOfAdd(DateTime.now().toDate());
        long categoryId = createRequest.getTransactionData().getCategoryId();
        if (categoryId != 0) {
            Category category = categoryRepository.getById(categoryId);
            transaction.setCategory(category);
        }
        transaction = transactionService.saveTransaction(transaction);
        TransactionEntityType entityType = mapper.map(transaction, TransactionEntityType.class);
        TransactionResponse response = new TransactionResponse();
        response.setTransactionDetails(entityType);
        return response;
    }
}
