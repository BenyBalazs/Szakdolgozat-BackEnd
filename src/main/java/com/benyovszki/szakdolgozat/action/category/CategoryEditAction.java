package com.benyovszki.szakdolgozat.action.category;

import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.service.impl.CategoryService;
import dto.szakdolgozat.benyovszki.com.category.CategoryEditRequest;
import dto.szakdolgozat.benyovszki.com.category.CategoryEntityType;
import dto.szakdolgozat.benyovszki.com.category.CategoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CategoryEditAction {

    private CategoryService categoryService;
    private ModelMapper mapper;

    @Transactional
    public CategoryResponse editCategory(CategoryEditRequest editRequest) {
        CategoryEntityType entityType = editRequest.getCategoryEntityData();
        Category category = categoryService.findById(entityType.getId());
        mapper.map(entityType, category);
        categoryService.save(category);
        return new CategoryResponse().withCategoryDetails(mapper.map(category, CategoryEntityType.class));
    }
}
