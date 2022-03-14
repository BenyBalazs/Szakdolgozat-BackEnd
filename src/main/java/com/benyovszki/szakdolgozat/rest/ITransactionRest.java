package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.http.HttpResponse;

@RequestMapping(RestPaths.BASIC_EXPENSE_PATH)
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
