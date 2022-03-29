package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.BalanceRequest;
import com.benyovszki.szakdolgozat.dto.BalanceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(RestPaths.BALANCE_PATH)
@CrossOrigin("*")
public interface IBalanceRest {

    @PostMapping
    ResponseEntity<BalanceResponse> getBalance(@RequestBody BalanceRequest balanceRequest);
}
