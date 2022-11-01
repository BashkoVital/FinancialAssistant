package com.bashko.itmo.services;

import com.bashko.itmo.entities.IncomeCategory;
import com.bashko.itmo.repositories.IncomeCategoryRepository;
import com.bashko.itmo.services.interfaceService.IncomeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeCategoryServiceJpaImpl implements IncomeCategoryService {

    private IncomeCategoryRepository incomeCategoryRepository;

    @Autowired
    public void setIncomeCategoryRepository(IncomeCategoryRepository incomeCategoryRepository) {
        this.incomeCategoryRepository = incomeCategoryRepository;
    }

    @Override
    public List<IncomeCategory> findAllByUserId(Long id) {
        return incomeCategoryRepository.findAllByFinAssistUserId(id);
    }

    @Override
    public IncomeCategory findByTitleSavCatAndUserId(String titleIncCat, Long id) {
        return incomeCategoryRepository.getIncomeCategoryByTitleIncCatAndFinAssistUserId(titleIncCat,id);
    }
}
