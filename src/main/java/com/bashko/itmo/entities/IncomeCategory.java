package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "income_category")
@Data
@NoArgsConstructor
public class IncomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idIncCat;

    @Column(name = "title", nullable = false)
    private String titleIncCat;

    @ManyToOne
    @JoinColumn(name = "fin_asist_id")
    private FinAssist finAssist;

    @OneToMany(mappedBy = "incomeCategory",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<SalaryIncome> salaryIncomeList;

    @OneToMany(mappedBy = "incomeCategory",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<InvestmentIncome> investmentIncomeList;

    @OneToMany(mappedBy = "incomeCategory",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<AnotherIncome> anotherIncomeList;

    public IncomeCategory(String titleIncCat, FinAssist finAssist) {
        this.titleIncCat = titleIncCat;
        this.finAssist = finAssist;
    }
}
