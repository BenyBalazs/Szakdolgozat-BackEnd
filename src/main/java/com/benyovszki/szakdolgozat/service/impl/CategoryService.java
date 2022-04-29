package com.benyovszki.szakdolgozat.service.impl;

import com.benyovszki.szakdolgozat.dto.response.QueryResponse;
import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.model.TransactionType;
import com.benyovszki.szakdolgozat.model.user.Role;
import com.benyovszki.szakdolgozat.repository.CategoryRepository;
import com.benyovszki.szakdolgozat.util.EnumConverter;
import dto.szakdolgozat.benyovszki.com.category.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private EntityManager em;
    private UserService userService;

    public Category findById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new OperationException(ErrorType.ENTITY_NOT_FOUND,
                        String.format("No Category with this id: [ %s ]", id)));
    }

    public Category save(Category category) {
        checkForAdminPrivilege(category);
        return categoryRepository.save(category);
    }

    public void delete(long id) {
        Optional<Category> check = categoryRepository.findById(id);
        checkForAdminPrivilege(check.orElse(null));
        categoryRepository.deleteById(id);
    }

    public QueryResponse<Category> findByQueryParams(int page, int rows, CategoryQueryParams categoryQueryParams) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> categoryRoot = cq.from(Category.class);
        Order order = cb.asc(categoryRoot.get("name"));
        cq.where(getPredicates(cb, categoryRoot, categoryQueryParams).toArray(new Predicate[0]));
        cq.orderBy(order);

        //count max result
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Category> categoryCountRoot = countQuery.from(Category.class);
        countQuery.where(getPredicates(cb, categoryCountRoot, categoryQueryParams).toArray(new Predicate[0]));
        countQuery.select(cb.count(categoryCountRoot));
        Long count = em.createQuery(countQuery).getSingleResult();

        TypedQuery<Category> tq = em.createQuery(cq);
        tq.setFirstResult((int) (rows * (page - 1)));
        tq.setMaxResults(rows);

        return QueryResponse.<Category>builder().responseData(tq.getResultList()).maxResults(count).build();
    }

    private List<Predicate> getPredicates(CriteriaBuilder cb, Root<Category> rt, CategoryQueryParams categoryQueryParams) {
        List<Predicate> predicates = new ArrayList<>();

        UserDetails userDetails =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Predicate globalPredicate = cb.isNull(rt.get("owner"));
        Predicate username = cb.equal(rt.get("owner"), userService.getByUsername(userDetails.getUsername()));

        predicates.add(cb.or(globalPredicate, username));

        if (Objects.isNull(categoryQueryParams)) {
            return new ArrayList<>();
        }

        if (StringUtils.hasText(categoryQueryParams.getName())) {
            predicates.add(cb.like(rt.get("name"), "%" + categoryQueryParams.getName() + "%"));
        }
        if (Objects.nonNull(categoryQueryParams.getTransactionType())) {
            predicates.add(cb.equal(rt.get("transactionType"), EnumConverter.convert(categoryQueryParams.getTransactionType(), TransactionType.class)));
        }

        return predicates;
    }

    private void checkForAdminPrivilege(Category category) {

        if(Objects.isNull(category)) {
            return;
        }

        if (Objects.isNull(category.getOwner()) && !SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ROLE_ADMIN)) {
            throw new OperationException(ErrorType.MODIFY_GLOBAL_TYPE, "Global type modification is only available for admin users");
        }

    }
}
