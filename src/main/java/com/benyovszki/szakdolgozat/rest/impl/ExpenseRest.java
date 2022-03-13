package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.dto.request.ExpenseCreateRequest;
import com.benyovszki.szakdolgozat.dto.request.ExpenseQueryRequest;
import com.benyovszki.szakdolgozat.dto.response.ExpenseResponse;
import com.benyovszki.szakdolgozat.rest.IExpenseRest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@AllArgsConstructor
public class ExpenseRest implements IExpenseRest {

    @Override
    public HttpResponse<ExpenseResponse> createExpense(ExpenseCreateRequest expenseCreateRequest) {
        return null;
    }

    @Override
    public HttpResponse<ExpenseResponse> editExpense(ExpenseCreateRequest expenseCreateRequest) {
        return null;
    }

    @Override
    public HttpResponse<ExpenseResponse> deleteExpense() {
        return null;
    }

    @Override
    public HttpResponse<ExpenseResponse> queryExpense(ExpenseQueryRequest expenseQueryRequest) {
        return null;
    }
}
