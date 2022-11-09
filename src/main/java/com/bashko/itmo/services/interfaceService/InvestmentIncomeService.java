package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.InvestmentIncome;
import java.util.List;

public interface InvestmentIncomeService {
    List<InvestmentIncome> findAllByUserId(Long id);;
    InvestmentIncome findById(Long id);
    void deleteById(Long id);
    void save(InvestmentIncome investmentIncome);

    double getSumValueInvIncByUserId(Long id);

    List<InvestmentIncome> findAllByDateMonthAndUserId(Long id);

    double getSumValueInvIncByDateMonthAndUserId(Long id);
}
