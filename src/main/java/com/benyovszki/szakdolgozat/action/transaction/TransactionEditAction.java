package com.benyovszki.szakdolgozat.action.transaction;

import dto.szakdolgozat.benyovszki.com.transaction.*;
import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.model.TransactionType;
import com.benyovszki.szakdolgozat.repository.TransactionRepository;
import com.benyovszki.szakdolgozat.repository.UserRepository;
import com.benyovszki.szakdolgozat.service.impl.TransactionService;
import com.benyovszki.szakdolgozat.service.impl.UserService;
import com.benyovszki.szakdolgozat.util.DateTimeUtil;
import com.benyovszki.szakdolgozat.util.EnumConverter;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class TransactionEditAction {

    private TransactionService transactionService;
    private ModelMapper mapper;

    @Transactional
    public TransactionResponse editTransaction(TransactionEditRequest editRequest) {
        //TODO:MEGNÉZNI TÉNYLEG ÖVÉ E A CUCC.
        TransactionEntityType entityType = editRequest.getTransactionEntityData();
        Transaction transaction = transactionService.getById(entityType.getId());

        if (!transaction.getOwner().getUsername().equals(editRequest.getOwner())) {
            throw new OperationException(ErrorType.YOU_DONT_OWN_THIS_ENTITY, "This entity was created by another user.");
        }

        transaction = mapper.map(entityType, Transaction.class);

        if (entityType.getCategory() != 0) {
            //TODO:KATEGÓRIA BEÁLLÍTÁSA.
        }
        transactionService.saveTransaction(transaction);

        return new TransactionResponse().withTransactionDetails(mapper.map(transaction, TransactionEntityType.class));
    }

}
