package com.bashko.itmo.controllers;

import com.bashko.itmo.entities.User;
import com.bashko.itmo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IncomeController {
    private SalaryIncomeServiceJpaImpl salaryIncomeServiceJpaImpl;
    private InvestmentIncomeServiceJpaImpl investmentsSavingServiceJpaImpl;
    private AnotherIncomeServiceJpaImpl anotherIncomeServiceJpaImpl;

    @Autowired
    public IncomeController(SalaryIncomeServiceJpaImpl salaryIncomeServiceJpaImpl,
                            InvestmentIncomeServiceJpaImpl investmentsSavingServiceJpaImpl,
                            AnotherIncomeServiceJpaImpl anotherIncomeServiceJpaImpl) {
        this.salaryIncomeServiceJpaImpl = salaryIncomeServiceJpaImpl;
        this.investmentsSavingServiceJpaImpl = investmentsSavingServiceJpaImpl;
        this.anotherIncomeServiceJpaImpl = anotherIncomeServiceJpaImpl;
    }

    @GetMapping(value = "/income/allTime")
    public String getIncomeByAllTime(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("salaryIncomeList", salaryIncomeServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("investmentIncomeList", investmentsSavingServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("anotherIncomeList", anotherIncomeServiceJpaImpl.findAllByUserId(user.getId()));
        return "income";
    }

    @GetMapping(value = "/income/currentMonth")
    public String getIncomeByCurrentMonth(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("salaryIncomeList", salaryIncomeServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("investmentIncomeList", investmentsSavingServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("anotherIncomeList", anotherIncomeServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        return "income";
    }

    @GetMapping(value = "/income/removeSalary/{id}")
    public String removeByIdSalary(@PathVariable(value = "id") Long idSalary) {
        salaryIncomeServiceJpaImpl.deleteById(idSalary);
        return "redirect:/income";
    }

    @GetMapping(value = "/income/removeInvInc/{id}")
    public String removeByIdInvInc(@PathVariable(value = "id") Long idInvInc) {
        investmentsSavingServiceJpaImpl.deleteById(idInvInc);
        return "redirect:/income";
    }

    @GetMapping(value = "/income/removeAnInc/{id}")
    public String removeByIdAnInc(@PathVariable(value = "id") Long idAnInc) {
        anotherIncomeServiceJpaImpl.deleteById(idAnInc);
        return "redirect:/income";
    }
}
