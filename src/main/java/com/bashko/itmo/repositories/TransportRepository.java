package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransportRepository extends JpaRepository<Transport,Long> {
    List<Transport> findAllByExpensesCategoryFinAssistUserId(Long id);
}
