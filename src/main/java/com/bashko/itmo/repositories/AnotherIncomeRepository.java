package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.AnotherIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnotherIncomeRepository extends JpaRepository<AnotherIncome,Long> {
    List<AnotherIncome> findAllByIncomeCategoryFinAssistUserId(Long id);

}
