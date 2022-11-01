package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.InvestmentsSaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentsSavingRepository extends JpaRepository<InvestmentsSaving,Long> {
    List<InvestmentsSaving> findAllBySavingCategoryFinAssistUserId(Long id);
}
