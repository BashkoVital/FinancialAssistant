package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.PropertyCapitalSaving;

import java.util.List;

public interface PropertyCapitalSavingService {
    List<PropertyCapitalSaving> findAllByUserId(Long id);
    PropertyCapitalSaving findById(Long id);
    void deleteById(Long id);
    void save(PropertyCapitalSaving propertyCapitalSaving);

    double getSumValuePropSavByUserId(Long id);

    List<PropertyCapitalSaving> findAllByDateMonthAndUserId(Long id);

    double getSumValuePropSavByDateMonthAndUserId(Long id);
}
