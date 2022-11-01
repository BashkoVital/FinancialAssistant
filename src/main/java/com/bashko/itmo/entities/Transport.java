package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transport")
@Data
@NoArgsConstructor
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idTransport;

    @Column(name = "title", nullable = false)
    private String titleTransport;

    @Column(name = "cost", nullable = false)
    private double costTransport;

    @Column(name = "date")
    private Date dateTransport;

    @ManyToOne(optional = false)
    private ExpensesCategory expensesCategory;

    public Transport(String titleTransport, double costTransport, ExpensesCategory expensesCategory) {
        this.titleTransport = titleTransport;
        this.costTransport = costTransport;
        this.dateTransport = new Date();
        this.expensesCategory = expensesCategory;
    }
}
