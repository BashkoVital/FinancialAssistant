package com.bashko.itmo.services;

import com.bashko.itmo.entities.AnotherIncome;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.AnotherIncomeRepository;
import com.bashko.itmo.repositories.IncomeCategoryRepository;
import com.bashko.itmo.services.interfaceService.AnotherIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class AnotherIncomeServiceJpaImpl implements AnotherIncomeService {
    private AnotherIncomeRepository anotherIncomeRepository;
    private IncomeCategoryRepository incomeCategoryRepository;

    @Autowired
    public AnotherIncomeServiceJpaImpl(AnotherIncomeRepository anotherIncomeRepository, IncomeCategoryRepository incomeCategoryRepository) {
        this.anotherIncomeRepository = anotherIncomeRepository;
        this.incomeCategoryRepository = incomeCategoryRepository;
    }

    @Override
    public List<AnotherIncome> findAllByUserId(Long id) {
        return anotherIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id);
    }

    @Override
    public AnotherIncome findById(Long id) {
        return anotherIncomeRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        anotherIncomeRepository.deleteById(id);
    }

    @Override
    public void save(AnotherIncome anotherIncome) {
        AnotherIncome anotherIncomeTemp = new AnotherIncome();
        anotherIncomeTemp.setTitleAnInc(anotherIncome.getTitleAnInc());
        anotherIncomeTemp.setValueAnInc(anotherIncome.getValueAnInc());
        anotherIncomeTemp.setDateAnInc(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        anotherIncomeTemp.setIncomeCategory(incomeCategoryRepository.getIncomeCategoryByTitleIncCatAndFinAssistUserId("AnotherIncome", user.getId()));
        anotherIncomeRepository.save(anotherIncomeTemp);
    }

    @Override
    public double getSumValueAnIncByUserId(Long id) {
        double count = 0;
        for (AnotherIncome ai : anotherIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            count += ai.getValueAnInc();
        }
        return count;
    }

    @Override
    public List<AnotherIncome> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<AnotherIncome> anotherIncomeList = new LinkedList<>();
        for (AnotherIncome ai : anotherIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            if (ai.getDateAnInc().getMonth() == calendar.get(Calendar.MONTH)) {
                anotherIncomeList.add(ai);
            }
        }
        return anotherIncomeList;
    }

    @Override
    public double getSumValueAnIncByDateMonthAndUserId(Long id) {
        double count = 0;
        Calendar calendar = Calendar.getInstance();
        for (AnotherIncome ai : anotherIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            if (ai.getDateAnInc().getMonth() == calendar.get(Calendar.MONTH)) {
                count += ai.getValueAnInc();
            }
        }
        return count;
    }
}
