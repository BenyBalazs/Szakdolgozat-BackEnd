package com.benyovszki.szakdolgozat.service.impl;

import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.repository.CategoryRepository;
import dto.szakdolgozat.benyovszki.com.category.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private EntityManager em;
    private ModelMapper mapper;

    public Category findById(long id) {
       return categoryRepository.findById(id)
               .orElseThrow(() -> new OperationException(ErrorType.ENTITY_NOT_FOUND,
                       String.format("No Category with this id: [ %s ]", id)));
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryQueryResponse findByQueryParams(CategoryQueryRequest categoryQueryRequest) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> categoryRoot = cq.from(Category.class);
        Order order = cb.asc(categoryRoot.get("name"));
        cq.where(getPredicates(cb,categoryRoot, categoryQueryRequest.getQueryParams()).toArray(new Predicate[0]));
        cq.orderBy(order);

        //count max result
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Category> categoryCountRoot = countQuery.from(Category.class);
        //Order countOrder = cb.asc(categoryCountRoot.get("name"));
        //countQuery.orderBy(countOrder);
        countQuery.where(getPredicates(cb, categoryCountRoot, categoryQueryRequest.getQueryParams()).toArray(new Predicate[0]));
        countQuery.select(cb.count(categoryCountRoot));
        Long count = em.createQuery(countQuery).getSingleResult();

        TypedQuery<Category> tq = em.createQuery(cq);
        tq.setFirstResult(categoryQueryRequest.getPage().intValue()-1);
        tq.setMaxResults(categoryQueryRequest.getResultsPerPage().intValue());

        System.out.println(count);
        CategoryQueryResponse response = new CategoryQueryResponse().withMaxElements(BigInteger.valueOf(count))
                .withList(buildList(tq.getResultList()));

        return response;
    }

    private List<Predicate> getPredicates(CriteriaBuilder cb, Root<Category> rt, CategoryQueryParams categoryQueryParams) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(categoryQueryParams.getName())) {
            predicates.add(cb.like(rt.get("name"), "%" + categoryQueryParams.getName() + "%" ));
        }
        if (Objects.nonNull(categoryQueryParams.getType())) {
            predicates.add(cb.equal(rt.get("transaction_type"), categoryQueryParams.getType()));
        }

        return predicates;
    }

    private CategoryListType buildList(List<Category> resultList) {
        CategoryListType list = new CategoryListType();
        for(var e : resultList) {
            list.withRows(mapper.map(e, CategoryEntityType.class));
        }
        return list;
    }
}
