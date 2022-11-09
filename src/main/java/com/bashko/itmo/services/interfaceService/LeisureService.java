package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.Leisure;

import java.io.IOException;
import java.util.List;

public interface LeisureService {
    List<Leisure> findAllByUserId(Long id);
    Leisure findById(Long id);
    void deleteById(Long id);
    void save(Leisure leisure);

    double getSumCostLeisureByUserId(Long id);

    List<Leisure> findAllByDateMonthAndUserId(Long id);

    double getSumCostLeisureByDateMonthAndUserId(Long id);
}
