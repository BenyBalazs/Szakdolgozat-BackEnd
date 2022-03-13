package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.request.ExpenseCreateRequest;
import com.benyovszki.szakdolgozat.dto.request.ExpenseQueryRequest;
import com.benyovszki.szakdolgozat.dto.response.ExpenseResponse;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RequestMapping(RestPaths.BASIC_EXPENSE_PATH)
@CrossOrigin("*")
public interface IExpenseRest {

     @PostMapping
     HttpResponse<ExpenseResponse> createExpense(@RequestBody ExpenseCreateRequest expenseCreateRequest);
     @PutMapping
     HttpResponse<ExpenseResponse> editExpense(@RequestBody ExpenseCreateRequest expenseCreateRequest);
     @DeleteMapping
     HttpResponse<ExpenseResponse> deleteExpense();
     @PostMapping(path = RestPaths.QUERY)
     HttpResponse<ExpenseResponse> queryExpense(@RequestBody ExpenseQueryRequest expenseQueryRequest);
}
