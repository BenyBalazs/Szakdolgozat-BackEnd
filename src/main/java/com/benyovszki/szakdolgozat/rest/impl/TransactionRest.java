package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.dto.request.expense.ExpenseCreateRequest;
import com.benyovszki.szakdolgozat.dto.request.expense.ExpenseQueryRequest;
import com.benyovszki.szakdolgozat.dto.response.expense.ExpenseResponse;
import com.benyovszki.szakdolgozat.rest.ITransactionRest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@AllArgsConstructor
public class TransactionRest implements ITransactionRest {

    @Override
    public HttpResponse<ExpenseResponse> createTransaction(ExpenseCreateRequest expenseCreateRequest) {
        return null;
    }

    @Override
    public HttpResponse<ExpenseResponse> editTransaction(ExpenseCreateRequest expenseCreateRequest) {
        return null;
    }

    @Override
    public HttpResponse<ExpenseResponse> deleteTransaction() {
        return null;
    }

    @Override
    public HttpResponse<ExpenseResponse> queryTransaction(ExpenseQueryRequest expenseQueryRequest) {
        return null;
    }
}
