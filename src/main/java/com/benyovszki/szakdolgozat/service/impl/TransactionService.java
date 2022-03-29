package com.benyovszki.szakdolgozat.service.impl;

import com.benyovszki.szakdolgozat.dto.response.QueryResponse;
import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.Transaction;
import com.benyovszki.szakdolgozat.model.TransactionType;
import com.benyovszki.szakdolgozat.repository.TransactionRepository;
import com.benyovszki.szakdolgozat.service.ITransactionService;
import com.benyovszki.szakdolgozat.util.DateTimeUtil;
import com.benyovszki.szakdolgozat.util.EnumConverter;
import dto.szakdolgozat.benyovszki.com.transaction.TransactionQueryParams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    private TransactionRepository transactionRepository;
    private EntityManager em;

    @Override
    public Transaction getById(long id) {

        return transactionRepository.findById(id).orElseThrow(() -> new OperationException(ErrorType.ENTITY_NOT_FOUND, "No Entity with this id was found"));
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(long id) {
        transactionRepository.deleteById(id);
    }

    public QueryResponse<Transaction> findByQueryParams(int page, int rows, TransactionQueryParams queryParams, long ownerID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> categoryRoot = cq.from(Transaction.class);
        Order order1 = cb.desc(categoryRoot.get("dateOfPayment"));
        Order order2 = cb.desc(categoryRoot.get("dateOfAdd"));
        cq.where(getPredicates(cb,categoryRoot, queryParams, ownerID).toArray(new Predicate[0]));
        cq.orderBy(order2, order1);

        //count max result
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Transaction> categoryCountRoot = countQuery.from(Transaction.class);
        countQuery.where(getPredicates(cb, categoryCountRoot, queryParams, ownerID).toArray(new Predicate[0]));
        countQuery.select(cb.count(categoryCountRoot));
        Long count = em.createQuery(countQuery).getSingleResult();

        TypedQuery<Transaction> tq = em.createQuery(cq);
        tq.setFirstResult((int) (rows * (page - 1)));
        tq.setMaxResults(rows);

        return QueryResponse.<Transaction>builder().responseData(tq.getResultList()).maxResults(count).build();
    }

    private List<Predicate> getPredicates(CriteriaBuilder cb, Root<Transaction> rt, TransactionQueryParams queryParams, long ownerID) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.isNull(queryParams)) {
            return new ArrayList<>();
        }

        if (ownerID != 0) {
            predicates.add(cb.equal(rt.get("owner"), ownerID));
        }
        if (StringUtils.hasText(queryParams.getName())) {
            predicates.add(cb.like(rt.get("name"), "%" + queryParams.getName() + "%" ));
        }
        if (Objects.nonNull(queryParams.getType())) {
            predicates.add(cb.equal(rt.get("type"), EnumConverter.convert(queryParams.getType(), TransactionType.class)));
        }
        if (queryParams.getCategoryId() != 0) {
            predicates.add(cb.equal(rt.get("category"), queryParams.getCategoryId()));
        }
        if (Objects.nonNull(queryParams.getDateOfPaymentFrom()) && Objects.nonNull(queryParams.getDateOfPaymentTo())) {
            Date from = DateTimeUtil.dtoTimeToDate(queryParams.getDateOfPaymentFrom());
            Date to = DateTimeUtil.toStartOfDate(queryParams.getDateOfPaymentTo());
            Predicate greaterThanOrEqualTo = cb.greaterThanOrEqualTo(rt.get("dateOfPayment"),from);
            Predicate lessThanOrEqualTo = cb.lessThanOrEqualTo(rt.get("dateOfPayment"), to);
            predicates.add(cb.and(greaterThanOrEqualTo, lessThanOrEqualTo));
        }

        return predicates;
    }
}
