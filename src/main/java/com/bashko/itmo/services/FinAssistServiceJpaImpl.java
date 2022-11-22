package com.bashko.itmo.services;

import com.bashko.itmo.entities.ExpensesCategory;
import com.bashko.itmo.entities.FinAssist;
import com.bashko.itmo.entities.IncomeCategory;
import com.bashko.itmo.entities.SavingCategory;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.repositories.FinAssistRepository;
import com.bashko.itmo.repositories.IncomeCategoryRepository;
import com.bashko.itmo.repositories.SavingCategoryRepository;
import com.bashko.itmo.services.interfaceService.FinAssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FinAssistServiceJpaImpl implements FinAssistService {

    private final FinAssistRepository finAssistRepository;

    private final IncomeCategoryRepository incomeCategoryRepository;
    private final ExpensesCategoryRepository expensesCategoryRepository;
    private final SavingCategoryRepository savingCategoryRepository;


    @Autowired
    public FinAssistServiceJpaImpl(FinAssistRepository finAssistRepository,
                                   IncomeCategoryRepository incomeCategoryRepository,
                                   ExpensesCategoryRepository expensesCategoryRepository,
                                   SavingCategoryRepository savingCategoryRepository) {
        this.finAssistRepository = finAssistRepository;
        this.incomeCategoryRepository = incomeCategoryRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
        this.savingCategoryRepository = savingCategoryRepository;
    }

    @Override
    public List<FinAssist> findAllByUserId(Long id) {
        return finAssistRepository.findAllByUserId(id);
    }

    @Override
    public FinAssist findByTitleFinAssistAndUserId(String titleFinAssist, Long id) {
        return finAssistRepository.getByTitleFinAssistAndUserId(titleFinAssist, id);
    }

    @Override
    public void deleteById(Long id) {
        finAssistRepository.deleteById(id);
    }

    @Override
    public void save(FinAssist finAssist) {
        finAssistRepository.save(finAssist);

        FinAssist finAssistLastAdded = findFinAssistLastAdded();

        if (finAssist.getTitleFinAssist().equals("Income")) {
            incomeCategoryRepository.save(new IncomeCategory("SalaryIncome", finAssistLastAdded));
            incomeCategoryRepository.save(new IncomeCategory("InvestmentIncome", finAssistLastAdded));
            incomeCategoryRepository.save(new IncomeCategory("AnotherIncome", finAssistLastAdded));
        }
        if (finAssist.getTitleFinAssist().equals("Saving")) {
            savingCategoryRepository.save(new SavingCategory("InvestmentsSaving", finAssistLastAdded));
            savingCategoryRepository.save(new SavingCategory("PropertyCapitalSaving", finAssistLastAdded));
            savingCategoryRepository.save(new SavingCategory("LiquidCapitalSaving", finAssistLastAdded));
        }
        if (finAssist.getTitleFinAssist().equals("Expenses")) {
            expensesCategoryRepository.save(new ExpensesCategory("Cafe", finAssistLastAdded));
            expensesCategoryRepository.save(new ExpensesCategory("Health", finAssistLastAdded));
            expensesCategoryRepository.save(new ExpensesCategory("Leisure", finAssistLastAdded));
            expensesCategoryRepository.save(new ExpensesCategory("Product", finAssistLastAdded));
            expensesCategoryRepository.save(new ExpensesCategory("Transport", finAssistLastAdded));
            expensesCategoryRepository.save(new ExpensesCategory("Others", finAssistLastAdded));
        }
    }


    private FinAssist findFinAssistLastAdded() {
        List<FinAssist> finAssistLinkedList = new LinkedList<>();
        finAssistLinkedList = finAssistRepository.findAll();
        return finAssistLinkedList.get(finAssistLinkedList.size() - 1);
    }
}
