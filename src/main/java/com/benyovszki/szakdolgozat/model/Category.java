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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
}
