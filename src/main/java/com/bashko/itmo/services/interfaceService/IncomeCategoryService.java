package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.IncomeCategory;

import java.util.List;

public interface IncomeCategoryService {
    List<IncomeCategory> findAllByUserId(Long id);
    IncomeCategory findByTitleSavCatAndUserId(String titleSavCat, Long id);
}
