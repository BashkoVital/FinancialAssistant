package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.SalaryIncome;

import java.util.List;

public interface SalaryIncomeService {
    List<SalaryIncome> findAllByUserId(Long id);
    SalaryIncome findById(Long id);
    void deleteById(Long id);
    void save(SalaryIncome salaryIncome);

    double getSumValueSalaryByUserId(Long id);
}
