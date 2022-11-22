package com.bashko.itmo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idProduct;

    @Column(name = "title", nullable = false)
    private String titleProduct;

    @Column(name = "cost", nullable = false)
    private double costProduct;

    @Column(name = "date")
    private Date dateProduct;

    @ManyToOne(optional = false)
    private ExpensesCategory expensesCategory;

    public Product(String titleProduct, double costProduct, ExpensesCategory expensesCategory) {
        this.titleProduct = titleProduct;
        this.costProduct = costProduct;
        this.dateProduct = new Date();
        this.expensesCategory = expensesCategory;
    }
}
