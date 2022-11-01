package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "liquid_capital")
@Data
@NoArgsConstructor
public class LiquidCapitalSaving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idLiqSav;

    @Column(name = "value", nullable = false)
    private double valueLiqSav;

    @Column(name = "date", nullable = false)
    private Date dateLiqSav;

    @ManyToOne(optional = false)
    private SavingCategory savingCategory;

    public LiquidCapitalSaving(double valueLiqSav, Date dateLiqSav, SavingCategory savingCategory) {
        this.valueLiqSav = valueLiqSav;
        this.dateLiqSav = dateLiqSav;
        this.savingCategory = savingCategory;
    }
}
