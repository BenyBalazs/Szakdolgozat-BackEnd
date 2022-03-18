package com.benyovszki.szakdolgozat.action.transaction;

import com.benyovszki.szakdolgozat.dto.response.QueryResponse;
import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.service.impl.TransactionService;
import dto.szakdolgozat.benyovszki.com.category.CategoryEntityType;
import dto.szakdolgozat.benyovszki.com.category.CategoryListType;
import dto.szakdolgozat.benyovszki.com.category.CategoryQueryResponse;
import dto.szakdolgozat.benyovszki.com.transaction.TransactionEntityType;
import dto.szakdolgozat.benyovszki.com.transaction.TransactionListType;
import dto.szakdolgozat.benyovszki.com.transaction.TransactionQueryRequest;
import dto.szakdolgozat.benyovszki.com.transaction.TransactionQueryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TransactionQueryAction {

    private TransactionService transactionService;
    private ModelMapper mapper;

    public TransactionQueryResponse queryTransaction(TransactionQueryRequest queryRequest) {

        int page = 1;
        int rows = 10;
        if (Objects.nonNull(queryRequest.getPage())) {
            page = queryRequest.getPage().intValue();
        }
        if (Objects.nonNull(queryRequest.getRow())) {
            rows = queryRequest.getRow().intValue();
        }
        QueryResponse<Transaction> transactionQueryResponse =
                transactionService.findByQueryParams(page, rows, queryRequest.getQueryParams());

        return buildResponse(transactionQueryResponse.getResponseData())
                .withMaxElements(BigInteger.valueOf(transactionQueryResponse.getMaxResults()));
    }

    private TransactionQueryResponse buildResponse(List<Transaction> responseData) {
        var response = new TransactionQueryResponse();
        for (var e : responseData ) {
            TransactionListType listType =
                    new TransactionListType().withTransactionDetail(mapper.map(e, TransactionEntityType.class));
            response.withList(listType);
        }
        return response;
    }
}
