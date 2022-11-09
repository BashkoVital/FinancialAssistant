package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.AnotherIncome;
import java.util.List;

public interface AnotherIncomeService {
    List<AnotherIncome> findAllByUserId(Long id);
    List<AnotherIncome> findAllByDateMonthAndUserId(Long id);
    AnotherIncome findById(Long id);
    void deleteById(Long id);
    void save(AnotherIncome anotherIncome);

    double getSumValueAnIncByUserId(Long id);

    double getSumValueAnIncByDateMonthAndUserId(Long id);
}
