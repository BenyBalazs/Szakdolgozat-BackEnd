package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.action.transaction.*;
import dto.szakdolgozat.benyovszki.com.transaction.*;
import com.benyovszki.szakdolgozat.rest.ITransactionRest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransactionRest implements ITransactionRest {

    private TransactionGetAction getAction;
    private TransactionCreateAction createAction;
    private TransactionEditAction editAction;
    private TransactionDeleteAction deleteAction;
    private TransactionQueryAction queryAction;

    @Override
    public ResponseEntity<TransactionResponse> getById(long id, String owner) {
        return ResponseEntity.status(HttpStatus.OK).body(getAction.getById(id));
    }

    @Override
    public ResponseEntity<TransactionResponse> createTransaction(TransactionCreateRequest transactionCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(createAction.createTransaction(transactionCreateRequest));
    }

    @Override
    public ResponseEntity<TransactionResponse> editTransaction(TransactionEditRequest transactionEditRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(editAction.editTransaction(transactionEditRequest));
    }

    @Override
    public ResponseEntity<TransactionResponse> deleteTransaction(long id, String owner) {
        deleteAction.deleteTransaction(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<TransactionQueryResponse> queryTransaction(TransactionQueryRequest transactionQueryRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(queryAction.queryTransaction(transactionQueryRequest));
    }
}
