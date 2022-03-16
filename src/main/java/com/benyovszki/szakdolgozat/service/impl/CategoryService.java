package com.benyovszki.szakdolgozat.service.impl;

import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

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
}
