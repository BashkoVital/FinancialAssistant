package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.FinAssist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinAssistRepository extends JpaRepository<FinAssist, Long> {
    List<FinAssist> findAllByUserId(Long id);

    FinAssist getByTitleFinAssistAndUserId(String titleFinAssist, Long id);
}
