package com.benyovszki.szakdolgozat.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class QueryResponse <T> {

    private List<T> responseData;
    private Long maxResults;
}
