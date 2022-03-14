package com.benyovszki.szakdolgozat.service.impl;

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
        return null;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction editTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public boolean deleteTransaction(long id) {
        return false;
    }
}
