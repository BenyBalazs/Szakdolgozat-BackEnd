package com.benyovszki.szakdolgozat.dto.response.transaction;

import com.benyovszki.szakdolgozat.model.Transaction;
import lombok.Data;

@Data
public class TransactionResponse {

    private Transaction transactionDetails;
}
