package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.request.expense.ExpenseCreateRequest;
import com.benyovszki.szakdolgozat.dto.request.expense.ExpenseQueryRequest;
import com.benyovszki.szakdolgozat.dto.response.expense.ExpenseResponse;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RequestMapping(RestPaths.BASIC_EXPENSE_PATH)
@CrossOrigin("*")
public interface ITransactionRest {

     @PostMapping
     HttpResponse<ExpenseResponse> createTransaction(@RequestBody ExpenseCreateRequest expenseCreateRequest);
     @PutMapping
     HttpResponse<ExpenseResponse> editTransaction(@RequestBody ExpenseCreateRequest expenseCreateRequest);
     @DeleteMapping
     HttpResponse<ExpenseResponse> deleteTransaction();
     @PostMapping(path = RestPaths.QUERY)
     HttpResponse<ExpenseResponse> queryTransaction(@RequestBody ExpenseQueryRequest expenseQueryRequest);
}
