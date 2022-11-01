package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cafe")
@Data
@NoArgsConstructor
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCafe;

    @Column(name = "title",nullable = false)
    private String titleCafe;

    @Column(name = "cost", nullable = false)
    private double costCafe;

    @Column(name = "date")
    private Date dateCafe;

    @ManyToOne(optional = false)
    private ExpensesCategory expensesCategory;

    public Cafe(String titleCafe, double costCafe, ExpensesCategory expensesCategory) {
        this.titleCafe = titleCafe;
        this.costCafe = costCafe;
        this.dateCafe = new Date();
        this.expensesCategory = expensesCategory;
    }

    public Cafe(String titleCafe, double costCafe, Date dateCafe) {
        this.titleCafe = titleCafe;
        this.costCafe = costCafe;
        this.dateCafe = dateCafe;
    }
}
