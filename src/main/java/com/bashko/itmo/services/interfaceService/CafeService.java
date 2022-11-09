package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.Cafe;
import com.bashko.itmo.entities.User;

import java.util.Date;
import java.util.List;

public interface CafeService {

    List<Cafe> findAllByUserId(Long id);
    List<Cafe> findAllByDateMonthAndUserId(Long id);
    Cafe findById(Long id);
    void deleteById(Long id);
    void save(Cafe cafe);

    double getSumCostCafeByUserId(Long id);

    double getSumCostCafeByDateMonthAndUserId(Long id);
}
