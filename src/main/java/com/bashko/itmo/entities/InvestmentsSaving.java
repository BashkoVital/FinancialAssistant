package com.bashko.itmo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "investments")
@Getter
@Setter
@NoArgsConstructor
public class InvestmentsSaving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idInvSav;

    @Column(name = "title", nullable = false)
    private String titleInvSav;

    @Column(name = "value", nullable = false)
    private double valueInvSav;

    @Column(name = "date")
    private Date dateInvSav;

    @ManyToOne(optional = false)
    private SavingCategory savingCategory;

    public InvestmentsSaving(String titleInvSav, double valueInvSav, Date dateInvSav, SavingCategory savingCategory) {
        this.titleInvSav = titleInvSav;
        this.valueInvSav = valueInvSav;
        this.dateInvSav = dateInvSav;
        this.savingCategory = savingCategory;
    }
}
