package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.ExpensesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpensesCategoryRepository extends JpaRepository<ExpensesCategory,Long> {
    List<ExpensesCategory> findAllByFinAssistUserId(Long id);
    ExpensesCategory getExpensesCategoryByTitleExpCatAndFinAssistUserId(String titleExpCat, Long id);
}
