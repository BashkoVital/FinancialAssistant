package com.bashko.itmo.services;

import com.bashko.itmo.entities.InvestmentIncome;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.IncomeCategoryRepository;
import com.bashko.itmo.repositories.InvestmentIncomeRepository;
import com.bashko.itmo.services.interfaceService.InvestmentIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class InvestmentIncomeServiceJpaImpl implements InvestmentIncomeService {
    private final InvestmentIncomeRepository investmentIncomeRepository;
    private final IncomeCategoryRepository incomeCategoryRepository;

    @Autowired
    public InvestmentIncomeServiceJpaImpl(InvestmentIncomeRepository investmentIncomeRepository, IncomeCategoryRepository incomeCategoryRepository) {
        this.investmentIncomeRepository = investmentIncomeRepository;
        this.incomeCategoryRepository = incomeCategoryRepository;
    }

    @Override
    public List<InvestmentIncome> findAllByUserId(Long id) {
        return investmentIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id);
    }

    @Override
    public InvestmentIncome findById(Long id) {
        return investmentIncomeRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        investmentIncomeRepository.deleteById(id);
    }

    @Override
    public void save(InvestmentIncome investmentIncome) {
        InvestmentIncome investmentIncomeTemp = new InvestmentIncome();
        investmentIncomeTemp.setTitleInvInc(investmentIncome.getTitleInvInc());
        investmentIncomeTemp.setValueInvInc(investmentIncome.getValueInvInc());
        investmentIncomeTemp.setDateInvInc(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        investmentIncomeTemp.setIncomeCategory(incomeCategoryRepository.getIncomeCategoryByTitleIncCatAndFinAssistUserId("InvestmentIncome", user.getId()));
        investmentIncomeRepository.save(investmentIncomeTemp);
    }

    @Override
    public double getSumValueInvIncByUserId(Long id) {
        double count = 0;
        for (InvestmentIncome ii : investmentIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            count += ii.getValueInvInc();
        }
        return count;
    }

    @Override
    public List<InvestmentIncome> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<InvestmentIncome> investmentIncomeList = new LinkedList<>();
        for (InvestmentIncome ii : investmentIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            if (ii.getDateInvInc().getMonth() == calendar.get(Calendar.MONTH)) {
                investmentIncomeList.add(ii);
            }
        }
        return investmentIncomeList;
    }

    @Override
    public double getSumValueInvIncByDateMonthAndUserId(Long id) {
        double count = 0;
        Calendar calendar = Calendar.getInstance();
        for (InvestmentIncome ii : investmentIncomeRepository.findAllByIncomeCategoryFinAssistUserId(id)) {
            if (ii.getDateInvInc().getMonth() == calendar.get(Calendar.MONTH)) {
                count += ii.getValueInvInc();
            }
        }
        return count;
    }
}
