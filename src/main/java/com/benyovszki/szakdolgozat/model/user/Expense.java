package com.benyovszki.szakdolgozat.model.user;

import javax.persistence.*;

@Entity
public class Expense extends BasicFinialData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;
}
