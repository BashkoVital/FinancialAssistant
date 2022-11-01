package com.bashko.itmo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "property_capital")
@Data
@NoArgsConstructor
public class PropertyCapitalSaving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPropSav;

    @Column(name = "title", nullable = false)
    private String titlePropSav;

    @Column(name = "value", nullable = false)
    private double valuePropSav;

    @Column(name = "date")
    private Date datePropSav;

    @ManyToOne(optional = false)
    private SavingCategory savingCategory;

    public PropertyCapitalSaving(String titlePropSav, double valuePropSav, Date datePropSav, SavingCategory savingCategory) {
        this.titlePropSav = titlePropSav;
        this.valuePropSav = valuePropSav;
        this.datePropSav = datePropSav;
        this.savingCategory = savingCategory;
    }
}
