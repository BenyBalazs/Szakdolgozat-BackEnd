package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.rest.ICategoryRest;
import dto.szakdolgozat.benyovszki.com.category.CategoryCreateRequest;
import dto.szakdolgozat.benyovszki.com.category.CategoryEditRequest;
import dto.szakdolgozat.benyovszki.com.category.CategoryQueryRequest;
import dto.szakdolgozat.benyovszki.com.category.CategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryRest implements ICategoryRest {

    @Override
    public ResponseEntity<CategoryResponse> getById(long id, String owner) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResponse> createCategory(CategoryCreateRequest expenseCreateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResponse> editCategory(CategoryEditRequest expenseCreateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResponse> deleteCategory(long id, String owner) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResponse> queryCategory(CategoryQueryRequest expenseQueryRequest) {
        return null;
    }
}
