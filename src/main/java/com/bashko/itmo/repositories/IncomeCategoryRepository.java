package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long> {
    List<IncomeCategory> findAllByFinAssistUserId(Long id);
    IncomeCategory getIncomeCategoryByTitleIncCatAndFinAssistUserId(String titleIncCat, Long id);
}
