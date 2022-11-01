package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.SavingCategory;

import java.util.List;

public interface SavingCategoryService {
    List<SavingCategory> findAllByUserId(Long id);
    SavingCategory findByTitleSavCatAndUserId(String titleSavCat, Long id);
}
