package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.LiquidCapitalSaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LiquidCapitalSavingRepository extends JpaRepository<LiquidCapitalSaving, Long> {
    List<LiquidCapitalSaving> findAllBySavingCategoryFinAssistUserId(Long id);
}
