package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.Others;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OthersRepository extends JpaRepository<Others,Long> {
    List<Others> findAllByExpensesCategoryFinAssistUserId(Long id);
}
