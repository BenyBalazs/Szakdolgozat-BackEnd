package com.benyovszki.szakdolgozat.repository;

import java.math.BigDecimal;
import java.util.Date;

import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.model.TransactionType;
import com.benyovszki.szakdolgozat.model.user.User;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT sum(t.amount) from Transaction t where t.owner = :id and t.type = :type and t.dateOfPayment between :fromDate and :toDate")
    BigDecimal getSum(@Param("id") User user, @Param("type") TransactionType type, @Param("fromDate") Date from, @Param("toDate") Date to);

}
