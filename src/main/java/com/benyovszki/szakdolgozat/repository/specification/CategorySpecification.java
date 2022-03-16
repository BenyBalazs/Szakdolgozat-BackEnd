package com.benyovszki.szakdolgozat.repository.specification;

import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.model.TransactionType;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

    static Specification<Category> nameLike(String name) {
        return (category, cq, cb) -> cb.like(category.get("name"), "%"+name+"%");
    }

    static Specification<Category> typeEquals(TransactionType type) {
        return (category, cq, cb) -> cb.equal(category.get("transaction_type"), type);
    }

}
