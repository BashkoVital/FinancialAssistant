package com.bashko.itmo.services;

import com.bashko.itmo.entities.InvestmentsSaving;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.SavingCategoryRepository;
import com.bashko.itmo.repositories.InvestmentsSavingRepository;
import com.bashko.itmo.services.interfaceService.InvestmentsSavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class InvestmentsSavingServiceJpaImpl implements InvestmentsSavingService {

    private final InvestmentsSavingRepository investmentsSavingRepository;
    private final SavingCategoryRepository savingCategoryRepository;

    @Autowired
    public InvestmentsSavingServiceJpaImpl(InvestmentsSavingRepository investmentsSavingRepository, SavingCategoryRepository savingCategoryRepository) {
        this.investmentsSavingRepository = investmentsSavingRepository;
        this.savingCategoryRepository = savingCategoryRepository;
    }

    @Override
    public List<InvestmentsSaving> findAllByUserId(Long id) {
        return investmentsSavingRepository.findAllBySavingCategoryFinAssistUserId(id);
    }

    @Override
    public InvestmentsSaving findById(Long id) {
        return investmentsSavingRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        investmentsSavingRepository.deleteById(id);
    }

    @Override
    public void save(InvestmentsSaving investmentsSaving) {
        InvestmentsSaving investmentsSavingTemp = new InvestmentsSaving();
        investmentsSavingTemp.setTitleInvSav(investmentsSaving.getTitleInvSav());
        investmentsSavingTemp.setValueInvSav(investmentsSaving.getValueInvSav());
        investmentsSavingTemp.setDateInvSav(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        investmentsSavingTemp.setSavingCategory(savingCategoryRepository.getSavingCategoryByTitleSavCatAndFinAssistUserId("InvestmentsSaving", user.getId()));
        investmentsSavingRepository.save(investmentsSavingTemp);
    }

    @Override
    public double getSumValueInvSavByUserId(Long id) {
        double count = 0;
        for (InvestmentsSaving is : investmentsSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            count += is.getValueInvSav();
        }
        return count;
    }

    @Override
    public List<InvestmentsSaving> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<InvestmentsSaving> investmentsSavingList = new LinkedList<>();
        for (InvestmentsSaving is : investmentsSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            if (is.getDateInvSav().getMonth() == calendar.get(Calendar.MONTH)) {
                investmentsSavingList.add(is);
            }
        }
        return investmentsSavingList;
    }

    @Override
    public double getSumValueInvSavByDateMonthAndUserId(Long id) {
        double count = 0;
        Calendar calendar = Calendar.getInstance();
        for (InvestmentsSaving is : investmentsSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            if (is.getDateInvSav().getMonth() == calendar.get(Calendar.MONTH)) {
                count += is.getValueInvSav();
            }
        }
        return count;
    }
}
