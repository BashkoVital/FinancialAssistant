package com.bashko.itmo.services;

import com.bashko.itmo.entities.InvestmentsSaving;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.SavingCategoryRepository;
import com.bashko.itmo.repositories.InvestmentsSavingRepository;
import com.bashko.itmo.services.interfaceService.InvestmentsSavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvestmentsSavingServiceJpaImpl implements InvestmentsSavingService {

    private InvestmentsSavingRepository investmentsSavingRepository;
    private SavingCategoryRepository savingCategoryRepository;

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
}
