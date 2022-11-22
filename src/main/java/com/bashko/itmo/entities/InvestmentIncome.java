package com.bashko.itmo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "investment_income")
@Getter
@Setter
@NoArgsConstructor
public class InvestmentIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idInvInc;

    @Column(name = "title", nullable = false)
    private String titleInvInc;

    @Column(name = "value", nullable = false)
    private double valueInvInc;

    @Column(name = "date")
    private Date dateInvInc;

    @ManyToOne(optional = false)
    private IncomeCategory incomeCategory;
}
