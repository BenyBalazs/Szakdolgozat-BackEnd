package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.rest.RestPaths;
import dto.szakdolgozat.benyovszki.com.category.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(RestPaths.CATEGORY_PATH)
@CrossOrigin("*")
public interface ICategoryRest {

    @GetMapping
    ResponseEntity<CategoryResponse> getById(@RequestParam long id, String owner);
    @PostMapping
    ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryCreateRequest expenseCreateRequest);
    @PutMapping
    ResponseEntity<CategoryResponse> editCategory(@RequestBody CategoryEditRequest expenseCreateRequest);
    @DeleteMapping()
    ResponseEntity<CategoryResponse> deleteCategory(@RequestParam long id, String owner);
    @PostMapping(path = RestPaths.QUERY)
    ResponseEntity<CategoryResponse> queryCategory(@RequestBody CategoryQueryRequest expenseQueryRequest);
}
