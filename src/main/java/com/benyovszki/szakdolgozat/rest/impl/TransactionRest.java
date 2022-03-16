package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.action.transaction.TransactionCreateAction;
import com.benyovszki.szakdolgozat.action.transaction.TransactionDeleteAction;
import com.benyovszki.szakdolgozat.action.transaction.TransactionEditAction;
import com.benyovszki.szakdolgozat.action.transaction.TransactionGetAction;
import dto.szakdolgozat.benyovszki.com.transaction.*;
import com.benyovszki.szakdolgozat.dto.response.transaction.TransactionQueryResponse;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.rest.ITransactionRest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@AllArgsConstructor
public class TransactionRest implements ITransactionRest {

    private TransactionGetAction getAction;
    private TransactionCreateAction createAction;
    private TransactionEditAction editAction;
    private TransactionDeleteAction deleteAction;

    @Override
    public ResponseEntity<TransactionResponse> getById(long id, String owner) {
        return ResponseEntity.status(HttpStatus.OK).body(getAction.getById(id));
    }

    @Override
    public ResponseEntity<TransactionResponse> createTransaction(TransactionCreateRequest expenseCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(createAction.createTransaction(expenseCreateRequest));
    }

    @Override
    public ResponseEntity<TransactionResponse> editTransaction(TransactionEditRequest expenseCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(editAction.editTransaction(expenseCreateRequest));
    }

    @Override
    public ResponseEntity<TransactionResponse> deleteTransaction(long id, String owner) {
        deleteAction.deleteTransaction(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<TransactionListResponse> queryTransaction(TransactionListRequest expenseQueryRequest) {
        return null;
    }
}
