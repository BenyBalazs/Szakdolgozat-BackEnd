package com.benyovszki.szakdolgozat.model;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class BasicFinancialData {

    private String name;
    private BigDecimal amount;
    private Date dateOfPayment;
    private Date dateOfAdd;
}
