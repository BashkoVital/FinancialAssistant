package com.bashko.itmo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "health")
@Getter
@Setter
@NoArgsConstructor
public class Health {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idHealth;

    @Column(name = "title", nullable = false)
    private String titleHealth;

    @Column(name = "cost", nullable = false)
    private double costHealth;

    @Column(name = "date")
    private Date dateHealth;

    @ManyToOne(optional = false)
    private ExpensesCategory expensesCategory;

    public Health(String titleHealth, double costHealth, ExpensesCategory expensesCategory) {
        this.titleHealth = titleHealth;
        this.costHealth = costHealth;
        this.dateHealth = new Date();
        this.expensesCategory = expensesCategory;
    }
}
