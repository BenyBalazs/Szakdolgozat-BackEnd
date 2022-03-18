package com.benyovszki.szakdolgozat.action.category;

import com.benyovszki.szakdolgozat.dto.response.QueryResponse;
import com.benyovszki.szakdolgozat.model.Category;
import com.benyovszki.szakdolgozat.service.impl.CategoryService;
import dto.szakdolgozat.benyovszki.com.category.CategoryEntityType;
import dto.szakdolgozat.benyovszki.com.category.CategoryListType;
import dto.szakdolgozat.benyovszki.com.category.CategoryQueryRequest;
import dto.szakdolgozat.benyovszki.com.category.CategoryQueryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CategoryQueryAction {

    private CategoryService categoryService;
    private ModelMapper mapper;

    public CategoryQueryResponse queryCategory(CategoryQueryRequest categoryQueryRequest) {

        int page = 1;
        int rows = 10;
        if (Objects.nonNull(categoryQueryRequest.getPage())) {
            page = categoryQueryRequest.getPage().intValue();
        }
        if (Objects.nonNull(categoryQueryRequest.getRow())) {
            rows = categoryQueryRequest.getRow().intValue();
        }
        QueryResponse<Category> categoryQueryResponse =
                categoryService.findByQueryParams(page, rows, categoryQueryRequest.getQueryParams());

        return buildResponse(categoryQueryResponse.getResponseData())
                .withMaxElements(BigInteger.valueOf(categoryQueryResponse.getMaxResults()));
    }

    private CategoryQueryResponse buildResponse(List<Category> responseData) {
        var response = new CategoryQueryResponse();
        for (var e : responseData ) {
            CategoryListType listType =
                    new CategoryListType().withCategoryEntity(mapper.map(e, CategoryEntityType.class));
            response.withList(listType);
        }
        return response;
    }
}
