package com.bashko.itmo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "another_income")
@Getter
@Setter
@NoArgsConstructor
public class AnotherIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idAnInc;

    @Column(name = "title", nullable = false)
    private String titleAnInc;

    @Column(name = "value", nullable = false)
    private double valueAnInc;

    @Column(name = "date")
    private Date dateAnInc;

    @ManyToOne(optional = false)
    private IncomeCategory incomeCategory;
}
