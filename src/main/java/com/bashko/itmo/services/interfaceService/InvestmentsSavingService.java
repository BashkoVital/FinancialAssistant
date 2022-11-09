package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.InvestmentsSaving;

import java.util.List;

public interface InvestmentsSavingService {
    List<InvestmentsSaving> findAllByUserId(Long id);
    List<InvestmentsSaving> findAllByDateMonthAndUserId(Long id);
    InvestmentsSaving findById(Long id);
    void deleteById(Long id);
    void save(InvestmentsSaving investmentsSaving);

    double getSumValueInvSavByUserId(Long id);

    double getSumValueInvSavByDateMonthAndUserId(Long id);
}
