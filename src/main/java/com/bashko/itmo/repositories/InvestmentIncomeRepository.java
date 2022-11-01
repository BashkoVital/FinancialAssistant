package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.InvestmentIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InvestmentIncomeRepository extends JpaRepository<InvestmentIncome,Long> {
    List<InvestmentIncome> findAllByIncomeCategoryFinAssistUserId(Long id);
}
