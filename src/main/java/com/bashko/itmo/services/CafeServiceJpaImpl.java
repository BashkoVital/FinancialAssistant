package com.bashko.itmo.services;

import com.bashko.itmo.entities.Cafe;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.CafeRepository;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.services.interfaceService.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CafeServiceJpaImpl implements CafeService {
    private CafeRepository cafeRepository;
    private ExpensesCategoryRepository expensesCategoryRepository;

    @Autowired
    public CafeServiceJpaImpl(CafeRepository cafeRepository, ExpensesCategoryRepository expensesCategoryRepository) {
        this.cafeRepository = cafeRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
    }

    @Override
    public List<Cafe> findAllByUserId(Long id) {
        return cafeRepository.findAllByExpensesCategoryFinAssistUserId(id);
    }

    @Override
    public Cafe findById(Long id) {
        return cafeRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        cafeRepository.deleteById(id);
    }

    @Override
    public void save(Cafe cafe) {
        Cafe cafeTemp = new Cafe();
        cafeTemp.setTitleCafe(cafe.getTitleCafe());
        cafeTemp.setCostCafe(cafe.getCostCafe());
        cafeTemp.setDateCafe(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        cafeTemp.setExpensesCategory(expensesCategoryRepository.getExpensesCategoryByTitleExpCatAndFinAssistUserId("Cafe", user.getId()));

        cafeRepository.save(cafeTemp);
    }

    @Override
    public double getSumCostCafeByUserId(Long id) {
        double count = 0;
        for (Cafe cafe : cafeRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            count += cafe.getCostCafe();
        }
        return count;
    }
}
