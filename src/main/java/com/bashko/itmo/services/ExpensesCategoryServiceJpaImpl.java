package com.bashko.itmo.services;

import com.bashko.itmo.entities.ExpensesCategory;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.services.interfaceService.ExpensesCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesCategoryServiceJpaImpl implements ExpensesCategoryService {
    private final ExpensesCategoryRepository expensesCategoryRepository;

    @Autowired
    public ExpensesCategoryServiceJpaImpl(ExpensesCategoryRepository expensesCategoryRepository) {
        this.expensesCategoryRepository = expensesCategoryRepository;
    }

    @Override
    public List<ExpensesCategory> findAllByUserId(Long id) {
        return expensesCategoryRepository.findAllByFinAssistUserId(id);
    }

    @Override
    public ExpensesCategory findByTitleSavCatAndUserId(String titleExpCat, Long id) {
        return expensesCategoryRepository.getExpensesCategoryByTitleExpCatAndFinAssistUserId(titleExpCat, id);
    }
}
