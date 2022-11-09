package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.Others;

import java.io.IOException;
import java.util.List;

public interface OthersService {
    List<Others> findAllByUserId(Long id);
    Others findById(Long id);
    void deleteById(Long id);
    void save(Others cafe);

    double getSumCostOthersByUserId(Long id);

    List<Others> findAllByDateMonthAndUserId(Long id);

    double getSumCostOthersByDateMonthAndUserId(Long id);
}
