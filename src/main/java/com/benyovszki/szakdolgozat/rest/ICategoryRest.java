package com.benyovszki.szakdolgozat.rest;

import dto.szakdolgozat.benyovszki.com.category.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(RestPaths.CATEGORY_PATH)
@CrossOrigin("*")
public interface ICategoryRest {

    @GetMapping
    ResponseEntity<CategoryResponse> getById(@RequestParam long id);
    @PostMapping
    ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest);
    @PutMapping
    ResponseEntity<CategoryResponse> editCategory(@RequestBody CategoryEditRequest categoryEditRequest);
    @DeleteMapping()
    ResponseEntity<CategoryResponse> deleteCategory(@RequestParam long id);
    @PostMapping(path = RestPaths.QUERY)
    ResponseEntity<CategoryQueryResponse> queryCategory(@RequestBody CategoryQueryRequest categoryQueryRequest);
}
