package com.benyovszki.szakdolgozat.rest;


import dto.szakdolgozat.benyovszki.com.transaction.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(RestPaths.TRANSACTION_PATH)
@CrossOrigin("*")
public interface ITransactionRest {

     @GetMapping
     ResponseEntity<TransactionResponse> getById(@RequestParam long id, String owner);
     @PostMapping
     ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionCreateRequest expenseCreateRequest);
     @PutMapping
     ResponseEntity<TransactionResponse> editTransaction(@RequestBody TransactionEditRequest expenseCreateRequest);
     @DeleteMapping()
     ResponseEntity<TransactionResponse> deleteTransaction(@RequestParam long id, String owner);
     @PostMapping(path = RestPaths.QUERY)
     ResponseEntity<TransactionListResponse> queryTransaction(@RequestBody TransactionListRequest expenseQueryRequest);
}
