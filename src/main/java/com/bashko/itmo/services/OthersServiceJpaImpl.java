package com.bashko.itmo.services;

import com.bashko.itmo.entities.Others;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.repositories.OthersRepository;
import com.bashko.itmo.services.interfaceService.OthersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OthersServiceJpaImpl implements OthersService {

    private OthersRepository othersRepository;
    private ExpensesCategoryRepository expensesCategoryRepository;

    @Autowired
    public OthersServiceJpaImpl(OthersRepository othersRepository, ExpensesCategoryRepository expensesCategoryRepository) {
        this.othersRepository = othersRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
    }

    @Autowired
    public void setOthersRepository(OthersRepository othersRepository) {
        this.othersRepository = othersRepository;
    }

    @Override
    public List<Others> findAllByUserId(Long id) {
        return othersRepository.findAllByExpensesCategoryFinAssistUserId(id);
    }

    @Override
    public Others findById(Long id) {
        return othersRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        othersRepository.deleteById(id);
    }

    @Override
    public void save(Others others) {
        Others othersTemp = new Others();
        othersTemp.setTitleOthers(others.getTitleOthers());
        othersTemp.setCostOthers(others.getCostOthers());
        othersTemp.setDateOthers(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        othersTemp.setExpensesCategory(expensesCategoryRepository.getExpensesCategoryByTitleExpCatAndFinAssistUserId("Others", user.getId()));
        othersRepository.save(othersTemp);
    }

    @Override
    public double getSumCostOthersByUserId(Long id) {
        double count = 0;
        for (Others others : othersRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            count += others.getCostOthers();
        }
        return count;
    }
}
