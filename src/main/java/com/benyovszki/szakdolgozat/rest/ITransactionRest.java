package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.*;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RequestMapping(RestPaths.BASIC_EXPENSE_PATH)
@CrossOrigin("*")
public interface ITransactionRest {

     @GetMapping
     HttpResponse<TransactionResponse> getById(@RequestParam String id);
     @PostMapping
     HttpResponse<TransactionResponse> createTransaction(@RequestBody TransactionCreateRequest expenseCreateRequest);
     @PutMapping
     HttpResponse<TransactionResponse> editTransaction(@RequestBody TransactionEditRequest expenseCreateRequest);
     @DeleteMapping
     HttpResponse<TransactionResponse> deleteTransaction();
     @PostMapping(path = RestPaths.QUERY)
     HttpResponse<TransactionListResponse> queryTransaction(@RequestBody TransactionListRequest expenseQueryRequest);
}
