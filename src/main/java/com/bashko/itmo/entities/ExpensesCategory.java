package com.bashko.itmo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "expenses_category")
@Getter
@Setter
@NoArgsConstructor
public class ExpensesCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idExpCat;

    @Column(name = "title", nullable = false)
    private String titleExpCat;

    @ManyToOne
    @JoinColumn(name = "fin_asist_id")
    private FinAssist finAssist;

    @OneToMany(mappedBy = "expensesCategory",
            cascade = CascadeType.ALL)
    private List<Cafe> cafeList;

    @OneToMany(mappedBy = "expensesCategory",
            cascade = CascadeType.ALL)
    private List<Health> healthList;

    @OneToMany(mappedBy = "expensesCategory",
            cascade = CascadeType.ALL)
    private List<Leisure> leisureList;

    @OneToMany(mappedBy = "expensesCategory",
            cascade = CascadeType.ALL)
    private List<Others> othersList;

    @OneToMany(mappedBy = "expensesCategory",
            cascade = CascadeType.ALL)
    private List<Product> productList;

    @OneToMany(mappedBy = "expensesCategory",
            cascade = CascadeType.ALL)
    private List<Transport> transportList;

    public ExpensesCategory(String titleExpCat, FinAssist finAssist) {
        this.titleExpCat = titleExpCat;
        this.finAssist = finAssist;
    }
}
