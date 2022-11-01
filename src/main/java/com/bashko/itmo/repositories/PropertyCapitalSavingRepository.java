package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.PropertyCapitalSaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyCapitalSavingRepository extends JpaRepository<PropertyCapitalSaving,Long> {
    List<PropertyCapitalSaving> findAllBySavingCategoryFinAssistUserId(Long id);
}
