package com.benyovszki.szakdolgozat.service.impl;

import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.repository.TransactionRepository;
import com.benyovszki.szakdolgozat.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    private TransactionRepository transactionRepository;

    @Override
    public Transaction getById(long id) {

        return transactionRepository.findById(id).orElseThrow(() -> new OperationException(ErrorType.ENTITY_NOT_FOUND, "No Entity with this id was found"));
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(long id) {
        transactionRepository.deleteById(id);
    }
}
