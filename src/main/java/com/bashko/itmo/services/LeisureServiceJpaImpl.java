package com.bashko.itmo.services;

import com.bashko.itmo.entities.Leisure;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.repositories.LeisureRepository;
import com.bashko.itmo.services.interfaceService.LeisureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class LeisureServiceJpaImpl implements LeisureService {

    private LeisureRepository leisureRepository;
    private ExpensesCategoryRepository expensesCategoryRepository;

    @Autowired
    public LeisureServiceJpaImpl(LeisureRepository leisureRepository, ExpensesCategoryRepository expensesCategoryRepository) {
        this.leisureRepository = leisureRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
    }

    @Autowired
    public void setLeisureRepository(LeisureRepository leisureRepository) {
        this.leisureRepository = leisureRepository;
    }

    @Override
    public List<Leisure> findAllByUserId(Long id) {
        return leisureRepository.findAllByExpensesCategoryFinAssistUserId(id);
    }

    @Override
    public Leisure findById(Long id) {
        return leisureRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        leisureRepository.deleteById(id);
    }

    @Override
    public void save(Leisure leisure) {
        Leisure leisureTemp = new Leisure();
        leisureTemp.setTitleLeisure(leisure.getTitleLeisure());
        leisureTemp.setCostLeisure(leisure.getCostLeisure());
        leisureTemp.setDateLeisure(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        leisureTemp.setExpensesCategory(expensesCategoryRepository.getExpensesCategoryByTitleExpCatAndFinAssistUserId("Leisure", user.getId()));
        leisureRepository.save(leisureTemp);
    }

    @Override
    public double getSumCostLeisureByUserId(Long id) {
        double count = 0;
        for (Leisure leisure : leisureRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            count += leisure.getCostLeisure();
        }
        return count;
    }

    @Override
    public List<Leisure> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<Leisure> leisureList = new LinkedList<>();
        for (Leisure leisure : leisureRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            if (leisure.getDateLeisure().getMonth() == calendar.get(Calendar.MONTH)) {
                leisureList.add(leisure);
            }
        }
        return leisureList;
    }

    @Override
    public double getSumCostLeisureByDateMonthAndUserId(Long id) {
        double count = 0;
        Calendar calendar = Calendar.getInstance();
        for (Leisure leisure : leisureRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            if (leisure.getDateLeisure().getMonth() == calendar.get(Calendar.MONTH)) {
                count += leisure.getCostLeisure();
            }
        }
        return count;
    }
}
