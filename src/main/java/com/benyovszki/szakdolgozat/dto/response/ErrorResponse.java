package com.benyovszki.szakdolgozat.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorResponse {

    private int errorCode;
    private String massage;
    private String exception;

}
