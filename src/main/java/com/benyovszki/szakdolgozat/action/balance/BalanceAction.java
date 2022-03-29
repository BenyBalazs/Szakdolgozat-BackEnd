package com.benyovszki.szakdolgozat.action.balance;

import java.util.Objects;

import com.benyovszki.szakdolgozat.action.BaseAction;
import com.benyovszki.szakdolgozat.dto.BalanceRequest;
import com.benyovszki.szakdolgozat.dto.BalanceResponse;
import com.benyovszki.szakdolgozat.rest.impl.BalanceRest;
import com.benyovszki.szakdolgozat.service.impl.BalanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BalanceAction extends BaseAction {

    private BalanceService balanceService;

    public BalanceResponse getBalance(BalanceRequest balanceRequest) {

        if (Objects.isNull(balanceRequest.getDateOfPaymentFrom()) || Objects.isNull(balanceRequest.getDateOfPaymentTo()) || Objects.isNull(
                balanceRequest.getOwner())) {
            throw missingOrIncompleteParam("Invalid request");
        }

        return new BalanceResponse().withBalance(balanceService.getBalance(balanceRequest));
    }

}
