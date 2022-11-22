package com.bashko.itmo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fin_assist")
@Getter
@Setter
@NoArgsConstructor
public class FinAssist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idFinAssist;

    @Column(name = "title", nullable = false)
    private String titleFinAssist;

    @OneToMany(mappedBy = "finAssist",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<IncomeCategory> incomeCategoryList;

    @OneToMany(mappedBy = "finAssist",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<SavingCategory> savingCategoryList;

    @OneToMany(mappedBy = "finAssist",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<ExpensesCategory> expensesCategoryList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FinAssist(String titleFinAssist, User user) {
        this.titleFinAssist = titleFinAssist;
        this.user = user;
    }
}
