package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.FinAssist;

import java.util.List;

public interface FinAssistService {
    List<FinAssist> findAllByUserId(Long id);

    FinAssist findByTitleFinAssistAndUserId(String titleFinAssist, Long id);

    void deleteById(Long id);

    void save(FinAssist finAssist);
}
