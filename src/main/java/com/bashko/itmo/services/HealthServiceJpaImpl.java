package com.bashko.itmo.services;

import com.bashko.itmo.entities.Health;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.repositories.HealthRepository;
import com.bashko.itmo.services.interfaceService.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class HealthServiceJpaImpl implements HealthService {

    private final HealthRepository healthRepository;
    private final ExpensesCategoryRepository expensesCategoryRepository;

    @Autowired
    public HealthServiceJpaImpl(HealthRepository healthRepository, ExpensesCategoryRepository expensesCategoryRepository) {
        this.healthRepository = healthRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
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

    @Override
    public List<Health> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<Health> healthList = new LinkedList<>();
        for (Health health : healthRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            if (health.getDateHealth().getMonth() == calendar.get(Calendar.MONTH)) {
                healthList.add(health);
            }
        }
        return healthList;
    }

    @Override
    public double getSumCostHealthByDateMonthAndUserId(Long id) {
        double count = 0;
        Calendar calendar = Calendar.getInstance();
        for (Health health : healthRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            if (health.getDateHealth().getMonth() == calendar.get(Calendar.MONTH)) {
                count += health.getCostHealth();
            }
        }
        return count;
    }
}
