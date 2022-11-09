package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe,Long> {

    List<Cafe> findAllByExpensesCategoryFinAssistUserId(Long id);
}
