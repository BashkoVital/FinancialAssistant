package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.Health;

import java.io.IOException;
import java.util.List;

public interface HealthService {
    List<Health> findAllByUserId(Long id);
    Health findById(Long id);
    void deleteById(Long id);
    void save(Health health);

    double getSumCostHealthByUserId(Long id);
}
