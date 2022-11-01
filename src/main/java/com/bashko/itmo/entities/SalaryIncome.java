package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salary")
@Data
@NoArgsConstructor
public class SalaryIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idSalary;

    @Column(name = "value", nullable = false)
    private double valueSalary;

    @Column(name = "date", nullable = false)
    private Date dateSalary;

    @ManyToOne(optional = false)
    private IncomeCategory incomeCategory;
}
