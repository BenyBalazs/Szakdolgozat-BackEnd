package com.benyovszki.szakdolgozat.dto.response.error;

import com.benyovszki.szakdolgozat.exception.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse extends ErrorResponse {

    private ErrorType errorType;
    private String servicePath;
    private String serviceName;
}
