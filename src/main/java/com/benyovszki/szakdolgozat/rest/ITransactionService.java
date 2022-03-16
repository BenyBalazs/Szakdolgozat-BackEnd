package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.model.Transaction;

public interface ITransactionService {

    Transaction getById(long id);
    Transaction saveTransaction(Transaction transaction);
    void deleteTransaction(long id);
}
