package com.bashko.itmo.services;

import com.bashko.itmo.entities.SalaryIncome;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.IncomeCategoryRepository;
import com.bashko.itmo.repositories.SalaryIncomeRepository;
import com.bashko.itmo.services.interfaceService.SalaryIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class SalaryIncomeServiceJpaImpl implements SalaryIncomeService {

    private final SalaryIncomeRepository salaryIncomeRepository;
    private final IncomeCategoryRepository incomeCategoryRepository;

    @Autowired
    public SalaryIncomeServiceJpaImpl(SalaryIncomeRepository salaryIncomeRepository, IncomeCategoryRepository incomeCategoryRepository) {
        this.salaryIncomeRepository = salaryIncomeRepository;
        this.incomeCategoryRepository = incomeCategoryRepository;
    }

    @Override
    public List<SalaryIncome> findAllByUserId(Long id) {
        return salaryIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id);
    }

    @Override
    public SalaryIncome findById(Long id) {
        return salaryIncomeRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        salaryIncomeRepository.deleteById(id);
    }

    @Override
    public void save(SalaryIncome salaryIncome) {
        SalaryIncome salaryIncomeTemp = new SalaryIncome();
        salaryIncomeTemp.setValueSalary(salaryIncome.getValueSalary());
        salaryIncomeTemp.setDateSalary(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        salaryIncomeTemp.setIncomeCategory(incomeCategoryRepository.getIncomeCategoryByTitleIncCatAndFinAssistUserId("SalaryIncome", user.getId()));
        salaryIncomeRepository.save(salaryIncomeTemp);
    }

    @Override
    public double getSumValueSalaryByUserId(Long id) {
        double count = 0;
        for (SalaryIncome si : salaryIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            count += si.getValueSalary();
        }
        return count;
    }

    @Override
    public List<SalaryIncome> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<SalaryIncome> salaryIncomeList = new LinkedList<>();
        for (SalaryIncome si : salaryIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            if (si.getDateSalary().getMonth() == calendar.get(Calendar.MONTH)) {
                salaryIncomeList.add(si);
            }
        }
        return salaryIncomeList;
    }

    @Override
    public double getSumValueSalaryByDateMonthAndUserId(Long id) {
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        for (SalaryIncome si : salaryIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            if (si.getDateSalary().getMonth() == calendar.get(Calendar.MONTH)) {
                count += si.getValueSalary();
            }
        }
        return count;
    }
}
