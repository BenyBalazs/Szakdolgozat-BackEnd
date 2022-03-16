package com.benyovszki.szakdolgozat.action.category;

import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.service.impl.CategoryService;
import dto.szakdolgozat.benyovszki.com.category.CategoryEntityType;
import dto.szakdolgozat.benyovszki.com.category.CategoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryGetAction {

    private CategoryService categoryService;
    private ModelMapper mapper;

    public CategoryResponse getById(long id) {
        Category category = categoryService.findById(id);
        return new CategoryResponse().withCategoryDetails(mapper.map(category, CategoryEntityType.class));
    }
}
