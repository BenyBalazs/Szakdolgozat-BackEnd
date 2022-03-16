package com.benyovszki.szakdolgozat.repository;

import com.benyovszki.szakdolgozat.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
