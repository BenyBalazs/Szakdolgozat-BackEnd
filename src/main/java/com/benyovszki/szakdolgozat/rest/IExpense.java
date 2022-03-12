package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.request.ExpenseCreateRequest;
import com.benyovszki.szakdolgozat.dto.request.ExpenseQueryRequest;
import com.benyovszki.szakdolgozat.dto.response.ExpenseResponse;
import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;

public interface IExpense {

     HttpResponse<ExpenseResponse> createExpense(ExpenseCreateRequest expenseCreateRequest);
     HttpResponse<ExpenseResponse> editExpense(ExpenseCreateRequest expenseCreateRequest);
     HttpResponse<ExpenseResponse> deleteExpense();
     HttpResponse<ExpenseResponse> queryExpense(ExpenseQueryRequest expenseQueryRequest);
}
