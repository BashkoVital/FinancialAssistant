package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.SalaryIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalaryIncomeRepository extends JpaRepository<SalaryIncome,Long> {
    List<SalaryIncome> findAllByIncomeCategoryFinAssistUserId(Long id);
}
