package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leisure")
@Data
@NoArgsConstructor
public class Leisure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idLeisure;

    @Column(name = "title", nullable = false)
    private String titleLeisure;

    @Column(name = "cost", nullable = false)
    private double costLeisure;

    @Column(name = "date")
    private Date dateLeisure;

    @ManyToOne(optional = false)
    private ExpensesCategory expensesCategory;

    public Leisure(String titleLeisure, double costLeisure, ExpensesCategory expensesCategory) {
        this.titleLeisure = titleLeisure;
        this.costLeisure = costLeisure;
        this.dateLeisure = new Date();
        this.expensesCategory = expensesCategory;
    }
}
