package com.benyovszki.szakdolgozat.action.transaction;

import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.service.impl.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionDeleteAction {

    private TransactionService transactionService;

    @Transactional
    public boolean deleteTransaction(long id) {

        //TODO: owner megnézése.
        transactionService.deleteTransaction(id);
        return true;
    }
}
