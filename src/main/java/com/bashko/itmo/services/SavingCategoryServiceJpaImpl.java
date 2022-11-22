package com.bashko.itmo.services;

import com.bashko.itmo.entities.SavingCategory;
import com.bashko.itmo.repositories.SavingCategoryRepository;
import com.bashko.itmo.services.interfaceService.SavingCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingCategoryServiceJpaImpl implements SavingCategoryService {

    private final SavingCategoryRepository savingCategoryRepository;

    @Autowired
    public SavingCategoryServiceJpaImpl(SavingCategoryRepository savingCategoryRepository) {
        this.savingCategoryRepository = savingCategoryRepository;
    }

    @Override
    public List<SavingCategory> findAllByUserId(Long id) {
        return savingCategoryRepository.findAllByFinAssistUserId(id);
    }

    @Override
    public SavingCategory findByTitleSavCatAndUserId(String titleSavCat, Long id) {
        return savingCategoryRepository.getSavingCategoryByTitleSavCatAndFinAssistUserId(titleSavCat, id);
    }
}
