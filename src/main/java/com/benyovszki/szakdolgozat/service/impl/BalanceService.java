package com.benyovszki.szakdolgozat.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.benyovszki.szakdolgozat.dto.BalanceRequest;
import com.benyovszki.szakdolgozat.model.TransactionType;
import com.benyovszki.szakdolgozat.model.user.User;
import com.benyovszki.szakdolgozat.repository.CategoryRepository;
import com.benyovszki.szakdolgozat.repository.TransactionRepository;
import com.benyovszki.szakdolgozat.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BalanceService {

    private UserService userService;
    private TransactionRepository transactionRepository;

    public BigDecimal getBalance(BalanceRequest balanceRequest) {
        User user = userService.getByUsername(balanceRequest.getOwner());
        Date from = DateTimeUtil.dtoTimeToDate(balanceRequest.getDateOfPaymentFrom());
        Date to = DateTimeUtil.dtoTimeToDate(balanceRequest.getDateOfPaymentTo());
        BigDecimal income = transactionRepository.getSum(user, TransactionType.INCOME, from, to);
        BigDecimal expense = transactionRepository.getSum(user, TransactionType.EXPENSE, from, to);

        if (Objects.isNull(income)) {
            income = BigDecimal.ZERO;
        }
        if (Objects.isNull(expense)) {
            expense = BigDecimal.ZERO;
        }
        return income.subtract(expense);
    }
}
