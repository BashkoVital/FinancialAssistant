package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.Transport;
import java.util.List;

public interface TransportService {

    List<Transport> findAllByUserId(Long id);
    Transport findById(Long id);
    void deleteById(Long id);
    void save(Transport transport);

    double getSumCostTransportByUserId(Long id);

    List<Transport> findAllByDateMonthAndUserId(Long id);

    double getSumCostTransportByDateMonthAndUserId(Long id);
}
