package com.bashko.itmo.controllers;

import com.bashko.itmo.entities.User;
import com.bashko.itmo.services.InvestmentsSavingServiceJpaImpl;
import com.bashko.itmo.services.LiquidCapitalSavingServiceJpaImpl;
import com.bashko.itmo.services.PropertyCapitalSavingServiceJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class SavingController {

    private InvestmentsSavingServiceJpaImpl investmentsSavingServiceJpaImpl;
    private PropertyCapitalSavingServiceJpaImpl propertyCapitalSavingServiceJpaImpl;
    private LiquidCapitalSavingServiceJpaImpl liquidCapitalSavingServiceJpaImpl;

    @Autowired
    public SavingController(InvestmentsSavingServiceJpaImpl investmentsSavingServiceJpaImpl,
                            PropertyCapitalSavingServiceJpaImpl propertyCapitalSavingServiceJpaImpl,
                            LiquidCapitalSavingServiceJpaImpl liquidCapitalSavingServiceJpaImpl) {
        this.investmentsSavingServiceJpaImpl = investmentsSavingServiceJpaImpl;
        this.propertyCapitalSavingServiceJpaImpl = propertyCapitalSavingServiceJpaImpl;
        this.liquidCapitalSavingServiceJpaImpl = liquidCapitalSavingServiceJpaImpl;
    }

    @GetMapping(value = "/saving/allTime")
    public String getSavingByAllTime(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("investmentsSavingList", investmentsSavingServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("propertyCapitalSavingList", propertyCapitalSavingServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("liquidCapitalSavingList", liquidCapitalSavingServiceJpaImpl.findAllByUserId(user.getId()));
        return "saving";
    }

    @GetMapping(value = "/saving/currentMonth")
    public String getSavingByCurrentMonth(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("investmentsSavingList", investmentsSavingServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("propertyCapitalSavingList", propertyCapitalSavingServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("liquidCapitalSavingList", liquidCapitalSavingServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        return "saving";
    }

    @GetMapping(value = "/saving/removeInvSav/{id}")
    public String removeByIdInvSav(@PathVariable(value = "id") Long idInvSav) {
        investmentsSavingServiceJpaImpl.deleteById(idInvSav);
        return "redirect:/saving/currentMonth";
    }

    @GetMapping(value = "/saving/removePropSav/{id}")
    public String removeByIdPropSav(@PathVariable(value = "id") Long idPropSav) {
        propertyCapitalSavingServiceJpaImpl.deleteById(idPropSav);
        return "redirect:/saving/currentMonth";
    }

    @GetMapping(value = "/saving/removeLiqSav/{id}")
    public String removeByIdLiqSav(@PathVariable(value = "id") Long idLiqSav) {
        liquidCapitalSavingServiceJpaImpl.deleteById(idLiqSav);
        return "redirect:/saving/currentMonth";
    }
}
