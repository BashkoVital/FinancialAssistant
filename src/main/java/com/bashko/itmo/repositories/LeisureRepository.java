package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.Leisure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LeisureRepository extends JpaRepository<Leisure, Long> {
    List<Leisure> findAllByExpensesCategoryFinAssistUserId(Long id);
}
