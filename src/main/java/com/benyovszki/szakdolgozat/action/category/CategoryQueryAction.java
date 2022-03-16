package com.benyovszki.szakdolgozat.action.category;

import com.benyovszki.szakdolgozat.service.impl.CategoryService;
import dto.szakdolgozat.benyovszki.com.category.CategoryQueryRequest;
import dto.szakdolgozat.benyovszki.com.category.CategoryQueryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryQueryAction {

    private CategoryService categoryService;

    public CategoryQueryResponse queryCategory(CategoryQueryRequest categoryQueryRequest) {

        return categoryService.findByQueryParams(categoryQueryRequest);
    }
}
