package com.benyovszki.szakdolgozat.service.impl;

import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.repository.CategoryRepository;
import dto.szakdolgozat.benyovszki.com.category.CategoryEntityType;
import dto.szakdolgozat.benyovszki.com.category.CategoryListType;
import dto.szakdolgozat.benyovszki.com.category.CategoryQueryRequest;
import dto.szakdolgozat.benyovszki.com.category.CategoryQueryResponse;
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
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(categoryQueryRequest.getName())) {
            predicates.add(cb.like(categoryRoot.get("name"), "%"+ categoryRoot + "%" ));
        }
        if (Objects.nonNull(categoryQueryRequest.getType())) {
            predicates.add(cb.equal(categoryRoot.get("transaction_type"), categoryQueryRequest.getType()));
        }
        Order order = cb.asc(categoryRoot.get("name"));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(order);
        //count max result
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.where(predicates.toArray(new Predicate[0]));
        countQuery.select(cb.count(countQuery.from(Category.class)));
        Long count = em.createQuery(countQuery).getSingleResult();

        TypedQuery<Category> tq = em.createQuery(cq);
        tq.setFirstResult(categoryQueryRequest.getPage().intValue()-1);
        tq.setMaxResults(categoryQueryRequest.getResultsPerPage().intValue());

        CategoryQueryResponse response = new CategoryQueryResponse().withMaxElements(BigInteger.valueOf(count))
                .withList(buildList(tq.getResultList()));

        return response;
    }

    private CategoryListType buildList(List<Category> resultList) {
        CategoryListType list = new CategoryListType();
        for(var e : resultList) {
            list.setRows(mapper.map(e, CategoryEntityType.class));
        }
        return list;
    }
}
