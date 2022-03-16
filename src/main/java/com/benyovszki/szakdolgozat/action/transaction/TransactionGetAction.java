package com.benyovszki.szakdolgozat.action.transaction;

import dto.szakdolgozat.benyovszki.com.transaction.*;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.service.impl.TransactionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionGetAction {

    private TransactionService transactionService;
    private ModelMapper mapper;

    public TransactionResponse getById(long id) {

        Transaction transaction = transactionService.getById(id);
        TransactionResponse response = new TransactionResponse();
        response.setTransactionDetails(mapper.map(transaction, TransactionEntityType.class));
        return response;
    }
}
