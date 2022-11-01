package com.bashko.itmo.services;

import com.bashko.itmo.entities.Health;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.repositories.HealthRepository;
import com.bashko.itmo.services.interfaceService.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HealthServiceJpaImpl implements HealthService {

    private HealthRepository healthRepository;
    private ExpensesCategoryRepository expensesCategoryRepository;

    @Autowired
    public HealthServiceJpaImpl(HealthRepository healthRepository, ExpensesCategoryRepository expensesCategoryRepository) {
        this.healthRepository = healthRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
    }

    @Autowired
    public void setHealthRepository(HealthRepository healthRepository) {
        this.healthRepository = healthRepository;
    }

    @Override
    public List<Health> findAllByUserId(Long id) {
        return healthRepository.findAllByExpensesCategoryFinAssistUserId(id);
    }

    @Override
    public Health findById(Long id) {
        return healthRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        healthRepository.deleteById(id);
    }

    @Override
    public void save(Health health) {
        Health healthTemp = new Health();
        healthTemp.setTitleHealth(health.getTitleHealth());
        healthTemp.setCostHealth(health.getCostHealth());
        healthTemp.setDateHealth(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        healthTemp.setExpensesCategory(expensesCategoryRepository.getExpensesCategoryByTitleExpCatAndFinAssistUserId("Health", user.getId()));

        healthRepository.save(healthTemp);
    }

    @Override
    public double getSumCostHealthByUserId(Long id) {
        double count = 0;
        for (Health health : healthRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            count += health.getCostHealth();
        }
        return count;
    }
}
