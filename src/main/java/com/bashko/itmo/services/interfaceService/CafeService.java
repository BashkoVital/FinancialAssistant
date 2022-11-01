package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.Cafe;
import com.bashko.itmo.entities.User;

import java.util.List;

public interface CafeService {

    List<Cafe> findAllByUserId(Long id);
    Cafe findById(Long id);
    void deleteById(Long id);
    void save(Cafe cafe);

    double getSumCostCafeByUserId(Long id);
}
