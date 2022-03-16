package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.action.category.*;
import com.benyovszki.szakdolgozat.rest.ICategoryRest;
import dto.szakdolgozat.benyovszki.com.category.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryRest implements ICategoryRest {

    CategoryGetAction getAction;
    CategoryCreateAction createAction;
    CategoryEditAction editAction;
    CategoryDeleteAction deleteAction;
    CategoryQueryAction queryAction;

    @Override
    public ResponseEntity<CategoryResponse> getById(long id) {
        return ResponseEntity.status(HttpStatus.OK).body(getAction.getById(id));
    }

    @Override
    public ResponseEntity<CategoryResponse> createCategory(CategoryCreateRequest categoryCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(createAction.createCategory(categoryCreateRequest));
    }

    @Override
    public ResponseEntity<CategoryResponse> editCategory(CategoryEditRequest categoryEditRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(editAction.editCategory(categoryEditRequest));
    }

    @Override
    public ResponseEntity<CategoryResponse> deleteCategory(long id) {
        deleteAction.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<CategoryQueryResponse> queryCategory(CategoryQueryRequest categoryQueryRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(queryAction.queryCategory(categoryQueryRequest));
    }
}
