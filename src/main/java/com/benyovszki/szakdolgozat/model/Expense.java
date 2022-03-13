package com.benyovszki.szakdolgozat.model;

import com.benyovszki.szakdolgozat.model.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Expense extends BasicFinancialData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private Type type;
}
