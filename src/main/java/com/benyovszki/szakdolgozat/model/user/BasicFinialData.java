package com.benyovszki.szakdolgozat.model.user;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class BasicFinialData {

    private String name;
    private BigDecimal amount;
    private Date dateOfPayment;
    private Date dateOfAdd;
}
