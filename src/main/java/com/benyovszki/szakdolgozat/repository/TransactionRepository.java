package com.benyovszki.szakdolgozat.repository;

import com.benyovszki.szakdolgozat.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
