package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.SavingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SavingCategoryRepository extends JpaRepository<SavingCategory,Long> {
    List<SavingCategory> findAllByFinAssistUserId(Long id);
    SavingCategory getSavingCategoryByTitleSavCatAndFinAssistUserId(String titleSavCat, Long id);
}
