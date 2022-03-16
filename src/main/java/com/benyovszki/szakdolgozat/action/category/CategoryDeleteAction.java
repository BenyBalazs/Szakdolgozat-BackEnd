package com.benyovszki.szakdolgozat.action.category;

import com.benyovszki.szakdolgozat.service.impl.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CategoryDeleteAction {

    private CategoryService categoryService;

    @Transactional
    public void deleteCategory(long id) {
        categoryService.delete(id);
    }
}
