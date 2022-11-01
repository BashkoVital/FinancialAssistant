package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.ExpensesCategory;
import com.bashko.itmo.entities.Health;

import java.util.List;

public interface ExpensesCategoryService {
    List<ExpensesCategory> findAllByUserId(Long id);
    ExpensesCategory findByTitleSavCatAndUserId(String titleSavCat, Long id);
}
