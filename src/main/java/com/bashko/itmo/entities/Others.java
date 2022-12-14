package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "others")
@Data
@NoArgsConstructor
public class Others {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idOthers;

    @Column(name = "title", nullable = false)
    private String titleOthers;

    @Column(name = "cost", nullable = false)
    private double costOthers;

    @Column(name = "date")
    private Date dateOthers;

    @ManyToOne(optional = false)
    private ExpensesCategory expensesCategory;

    public Others(String titleOthers, double costOthers, ExpensesCategory expensesCategory) {
        this.titleOthers = titleOthers;
        this.costOthers = costOthers;
        this.dateOthers = new Date();
        this.expensesCategory = expensesCategory;
    }
}
