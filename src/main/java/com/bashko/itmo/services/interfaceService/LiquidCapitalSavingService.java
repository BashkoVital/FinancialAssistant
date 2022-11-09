package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.LiquidCapitalSaving;

import java.util.List;

public interface LiquidCapitalSavingService {
    List<LiquidCapitalSaving> findAllByUserId(Long id);
    LiquidCapitalSaving findById(Long id);
    void deleteById(Long id);
    void save(LiquidCapitalSaving liquidCapitalSaving);

    double getSumValueLiqSavByUserId(Long id);

    List<LiquidCapitalSaving> findAllByDateMonthAndUserId(Long id);

    double getSumValueLiqSavByDateMonthAndUserId(Long id);
}
