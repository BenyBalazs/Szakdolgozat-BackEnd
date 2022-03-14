package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.dto.*;
import com.benyovszki.szakdolgozat.dto.response.transaction.TransactionQueryResponse;
import com.benyovszki.szakdolgozat.rest.ITransactionRest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@AllArgsConstructor
public class TransactionRest implements ITransactionRest {

    @Override
    public HttpResponse<TransactionResponse> getById(String id) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResponse> createTransaction(TransactionCreateRequest expenseCreateRequest) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResponse> editTransaction(TransactionEditRequest expenseCreateRequest) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResponse> deleteTransaction() {
        return null;
    }

    @Override
    public HttpResponse<TransactionListResponse> queryTransaction(TransactionListRequest expenseQueryRequest) {
        return null;
    }
}
