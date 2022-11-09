package com.bashko.itmo.services;

import com.bashko.itmo.entities.LiquidCapitalSaving;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.SavingCategoryRepository;
import com.bashko.itmo.repositories.LiquidCapitalSavingRepository;
import com.bashko.itmo.services.interfaceService.LiquidCapitalSavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class LiquidCapitalSavingServiceJpaImpl implements LiquidCapitalSavingService {
    private LiquidCapitalSavingRepository liquidCapitalSavingRepository;
    private SavingCategoryRepository savingCategoryRepository;

    @Autowired
    public LiquidCapitalSavingServiceJpaImpl(LiquidCapitalSavingRepository liquidCapitalSavingRepository,
                                             SavingCategoryRepository savingCategoryRepository) {
        this.liquidCapitalSavingRepository = liquidCapitalSavingRepository;
        this.savingCategoryRepository = savingCategoryRepository;
    }

    @Override
    public List<LiquidCapitalSaving> findAllByUserId(Long id) {
        return liquidCapitalSavingRepository.findAllBySavingCategoryFinAssistUserId(id);
    }

    @Override
    public LiquidCapitalSaving findById(Long id) {
        return liquidCapitalSavingRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        liquidCapitalSavingRepository.deleteById(id);
    }

    @Override
    public void save(LiquidCapitalSaving liquidCapitalSaving) {
        LiquidCapitalSaving liquidCapitalSavingTemp = new LiquidCapitalSaving();
        liquidCapitalSavingTemp.setValueLiqSav(liquidCapitalSaving.getValueLiqSav());
        liquidCapitalSavingTemp.setDateLiqSav(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        liquidCapitalSavingTemp.setSavingCategory(savingCategoryRepository.getSavingCategoryByTitleSavCatAndFinAssistUserId("LiquidCapitalSaving", user.getId()));
        liquidCapitalSavingRepository.save(liquidCapitalSavingTemp);
    }

    @Override
    public double getSumValueLiqSavByUserId(Long id) {
        double count = 0;
        for (LiquidCapitalSaving lcs : liquidCapitalSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            count += lcs.getValueLiqSav();
        }
        return count;
    }

    @Override
    public List<LiquidCapitalSaving> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<LiquidCapitalSaving> liquidCapitalSavingList = new LinkedList<>();
        for (LiquidCapitalSaving lcs : liquidCapitalSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            if (lcs.getDateLiqSav().getMonth() == calendar.get(Calendar.MONTH)) {
                liquidCapitalSavingList.add(lcs);
            }
        }
        return liquidCapitalSavingList;
    }

    @Override
    public double getSumValueLiqSavByDateMonthAndUserId(Long id) {
        double count = 0;
        Calendar calendar = Calendar.getInstance();
        for (LiquidCapitalSaving lcs : liquidCapitalSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            if (lcs.getDateLiqSav().getMonth() == calendar.get(Calendar.MONTH)) {
                count += lcs.getValueLiqSav();
            }
        }
        return count;
    }
}
