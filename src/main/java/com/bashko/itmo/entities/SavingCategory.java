package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "saving_category")
@Data
@NoArgsConstructor
public class SavingCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idSavCat;

    @Column(name = "title", nullable = false)
    private String titleSavCat;

    @ManyToOne
    @JoinColumn(name = "fin_asist_id")
    private FinAssist finAssist;

    @OneToMany(
            mappedBy = "savingCategory",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<InvestmentsSaving> investmentsSavingList;

    @OneToMany(
            mappedBy = "savingCategory",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<PropertyCapitalSaving> propertyCapitalSavingList;

    @OneToMany(
            mappedBy = "savingCategory",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<LiquidCapitalSaving> liquidCapitalSavingList;

    public SavingCategory(String titleSavCat, FinAssist finAssist) {
        this.titleSavCat = titleSavCat;
        this.finAssist = finAssist;
    }
}
