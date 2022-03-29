package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.action.balance.BalanceAction;
import com.benyovszki.szakdolgozat.dto.BalanceRequest;
import com.benyovszki.szakdolgozat.dto.BalanceResponse;
import com.benyovszki.szakdolgozat.rest.IBalanceRest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BalanceRest implements IBalanceRest {

    private BalanceAction balanceAction;

    @Override
    public ResponseEntity<BalanceResponse> getBalance(BalanceRequest balanceRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(balanceAction.getBalance(balanceRequest));
    }
}
