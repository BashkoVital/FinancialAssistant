package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.Health;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HealthRepository extends JpaRepository<Health,Long> {
    List<Health> findAllByExpensesCategoryFinAssistUserId(Long id);
}
