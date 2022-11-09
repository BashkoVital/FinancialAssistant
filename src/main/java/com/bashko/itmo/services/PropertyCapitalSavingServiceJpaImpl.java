package com.bashko.itmo.services;

import com.bashko.itmo.entities.PropertyCapitalSaving;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.SavingCategoryRepository;
import com.bashko.itmo.repositories.PropertyCapitalSavingRepository;
import com.bashko.itmo.services.interfaceService.PropertyCapitalSavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PropertyCapitalSavingServiceJpaImpl implements PropertyCapitalSavingService {

    private PropertyCapitalSavingRepository propertyCapitalSavingRepository;
    private SavingCategoryRepository savingCategoryRepository;

    @Autowired
    public PropertyCapitalSavingServiceJpaImpl(PropertyCapitalSavingRepository propertyCapitalSavingRepository, SavingCategoryRepository savingCategoryRepository) {
        this.propertyCapitalSavingRepository = propertyCapitalSavingRepository;
        this.savingCategoryRepository = savingCategoryRepository;
    }

    @Override
    public List<PropertyCapitalSaving> findAllByUserId(Long id) {
        return propertyCapitalSavingRepository.findAllBySavingCategoryFinAssistUserId(id);
    }

    @Override
    public PropertyCapitalSaving findById(Long id) {
        return propertyCapitalSavingRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        propertyCapitalSavingRepository.deleteById(id);
    }

    @Override
    public void save(PropertyCapitalSaving propertyCapitalSaving) {
        PropertyCapitalSaving propertyCapitalSavingTemp = new PropertyCapitalSaving();
        propertyCapitalSavingTemp.setTitlePropSav(propertyCapitalSaving.getTitlePropSav());
        propertyCapitalSavingTemp.setValuePropSav(propertyCapitalSaving.getValuePropSav());
        propertyCapitalSavingTemp.setDatePropSav(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        propertyCapitalSavingTemp.setSavingCategory(savingCategoryRepository.getSavingCategoryByTitleSavCatAndFinAssistUserId("PropertyCapitalSaving", user.getId()));
        propertyCapitalSavingRepository.save(propertyCapitalSavingTemp);
    }

    @Override
    public double getSumValuePropSavByUserId(Long id) {
        double count = 0;
        for (PropertyCapitalSaving pcs : propertyCapitalSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            count += pcs.getValuePropSav();
        }
        return count;
    }

    @Override
    public List<PropertyCapitalSaving> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<PropertyCapitalSaving> propertyCapitalSavingList = new LinkedList<>();
        for (PropertyCapitalSaving pcs : propertyCapitalSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            if (pcs.getDatePropSav().getMonth() == calendar.get(Calendar.MONTH)) {
                propertyCapitalSavingList.add(pcs);
            }
        }
        return propertyCapitalSavingList;
    }

    @Override
    public double getSumValuePropSavByDateMonthAndUserId(Long id) {
        double count = 0;
        Calendar calendar = Calendar.getInstance();
        for (PropertyCapitalSaving pcs : propertyCapitalSavingRepository.findAllBySavingCategoryFinAssistUserId(id)) {
            if (pcs.getDatePropSav().getMonth() == calendar.get(Calendar.MONTH)) {
                count += pcs.getValuePropSav();
            }
        }
        return count;
    }
}
