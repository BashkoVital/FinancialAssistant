package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByExpensesCategoryFinAssistUserId(Long id);
}
