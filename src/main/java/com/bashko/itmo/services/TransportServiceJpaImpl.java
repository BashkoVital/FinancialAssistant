package com.bashko.itmo.services;

import com.bashko.itmo.entities.Transport;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.repositories.TransportRepository;
import com.bashko.itmo.services.interfaceService.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransportServiceJpaImpl implements TransportService {

    private TransportRepository transportRepository;
    private ExpensesCategoryRepository expensesCategoryRepository;

    @Autowired
    public TransportServiceJpaImpl(TransportRepository transportRepository, ExpensesCategoryRepository expensesCategoryRepository) {
        this.transportRepository = transportRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
    }

    @Autowired
    public void setTransportRepository(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    public List<Transport> findAllByUserId(Long id) {
        return transportRepository.findAllByExpensesCategoryFinAssistUserId(id);
    }

    @Override
    public Transport findById(Long id) {
        return transportRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        transportRepository.deleteById(id);
    }

    @Override
    public void save(Transport transport) {
        Transport transportTemp = new Transport();
        transportTemp.setTitleTransport(transport.getTitleTransport());
        transportTemp.setCostTransport(transport.getCostTransport());
        transportTemp.setDateTransport(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        transportTemp.setExpensesCategory(expensesCategoryRepository.getExpensesCategoryByTitleExpCatAndFinAssistUserId("Transport", user.getId()));
        transportRepository.save(transportTemp);
    }

    @Override
    public double getSumCostTransportByUserId(Long id) {
        double count = 0;
        for (Transport transport : transportRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            count += transport.getCostTransport();
        }
        return count;
    }
}
