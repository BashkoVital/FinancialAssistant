package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAllByUserId(Long id);
    Product findById(Long id);
    void deleteById(Long id);
    void save(Product product);

    double getSumCostProductByUserId(Long id);
}
