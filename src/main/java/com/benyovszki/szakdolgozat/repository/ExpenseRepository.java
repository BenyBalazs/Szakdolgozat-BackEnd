package com.benyovszki.szakdolgozat.repository;

import com.benyovszki.szakdolgozat.model.Expense;
import com.benyovszki.szakdolgozat.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
