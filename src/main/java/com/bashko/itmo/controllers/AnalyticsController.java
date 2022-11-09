package com.bashko.itmo.controllers;

import com.bashko.itmo.entities.*;
import com.bashko.itmo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyticsController {

    private SalaryIncomeServiceJpaImpl salaryIncomeServiceJpaImpl;
    private InvestmentIncomeServiceJpaImpl investmentIncomeServiceJpaImpl;
    private AnotherIncomeServiceJpaImpl anotherIncomeServiceJpaImpl;

    private InvestmentsSavingServiceJpaImpl investmentsSavingServiceJpaImpl;
    private PropertyCapitalSavingServiceJpaImpl propertyCapitalSavingServiceJpaImpl;
    private LiquidCapitalSavingServiceJpaImpl liquidCapitalSavingServiceJpaImpl;


    private CafeServiceJpaImpl cafeServiceJpaImpl;
    private HealthServiceJpaImpl healthServiceJpaImpl;
    private LeisureServiceJpaImpl leisureServiceJpaImpl;
    private ProductServiceJpaImpl productServiceJpaImpl;
    private TransportServiceJpaImpl transportServiceJpaImpl;
    private OthersServiceJpaImpl othersServiceJpaImpl;

    @Autowired
    public AnalyticsController(SalaryIncomeServiceJpaImpl salaryIncomeServiceJpaImpl,
                               InvestmentIncomeServiceJpaImpl investmentIncomeServiceJpaImpl,
                               AnotherIncomeServiceJpaImpl anotherIncomeServiceJpaImpl,
                               InvestmentsSavingServiceJpaImpl investmentsSavingServiceJpaImpl,
                               PropertyCapitalSavingServiceJpaImpl propertyCapitalSavingServiceJpaImpl,
                               LiquidCapitalSavingServiceJpaImpl liquidCapitalSavingServiceJpaImpl,
                               CafeServiceJpaImpl cafeServiceJpaImpl,
                               HealthServiceJpaImpl healthServiceJpaImpl,
                               LeisureServiceJpaImpl leisureServiceJpaImpl,
                               ProductServiceJpaImpl productServiceJpaImpl,
                               TransportServiceJpaImpl transportServiceJpaImpl,
                               OthersServiceJpaImpl othersServiceJpaImpl) {
        this.salaryIncomeServiceJpaImpl = salaryIncomeServiceJpaImpl;
        this.investmentIncomeServiceJpaImpl = investmentIncomeServiceJpaImpl;
        this.anotherIncomeServiceJpaImpl = anotherIncomeServiceJpaImpl;
        this.investmentsSavingServiceJpaImpl = investmentsSavingServiceJpaImpl;
        this.propertyCapitalSavingServiceJpaImpl = propertyCapitalSavingServiceJpaImpl;
        this.liquidCapitalSavingServiceJpaImpl = liquidCapitalSavingServiceJpaImpl;
        this.cafeServiceJpaImpl = cafeServiceJpaImpl;
        this.healthServiceJpaImpl = healthServiceJpaImpl;
        this.leisureServiceJpaImpl = leisureServiceJpaImpl;
        this.productServiceJpaImpl = productServiceJpaImpl;
        this.transportServiceJpaImpl = transportServiceJpaImpl;
        this.othersServiceJpaImpl = othersServiceJpaImpl;
    }

    @GetMapping(value = "/analytics/allTime")
    String getAnalyticsByAllTime(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("valueSalaryAttr", salaryIncomeServiceJpaImpl.getSumValueSalaryByUserId(user.getId()));
        model.addAttribute("valueInvIncAttr", investmentIncomeServiceJpaImpl.getSumValueInvIncByUserId(user.getId()));
        model.addAttribute("valueAnIncAttr", anotherIncomeServiceJpaImpl.getSumValueAnIncByUserId(user.getId()));

        model.addAttribute("valueInvSavAttr", investmentsSavingServiceJpaImpl.getSumValueInvSavByUserId(user.getId()));
        model.addAttribute("valuePropSavAttr", propertyCapitalSavingServiceJpaImpl.getSumValuePropSavByUserId(user.getId()));
        model.addAttribute("valueLiqSavAttr", liquidCapitalSavingServiceJpaImpl.getSumValueLiqSavByUserId(user.getId()));

        model.addAttribute("costCafeAttr", cafeServiceJpaImpl.getSumCostCafeByUserId(user.getId()));
        model.addAttribute("costHealthAttr", healthServiceJpaImpl.getSumCostHealthByUserId(user.getId()));
        model.addAttribute("costLeisureAttr", leisureServiceJpaImpl.getSumCostLeisureByUserId(user.getId()));
        model.addAttribute("costProductAttr", productServiceJpaImpl.getSumCostProductByUserId(user.getId()));
        model.addAttribute("costTransportAttr", transportServiceJpaImpl.getSumCostTransportByUserId(user.getId()));
        model.addAttribute("costOthersAttr", othersServiceJpaImpl.getSumCostOthersByUserId(user.getId()));
        return "allTime";
    }

    @GetMapping(value = "/analytics/currentMonth")
    String getAnalyticsByCurrentMonth(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("valueSalaryAttr", salaryIncomeServiceJpaImpl.getSumValueSalaryByDateMonthAndUserId(user.getId()));
        model.addAttribute("valueInvIncAttr", investmentIncomeServiceJpaImpl.getSumValueInvIncByDateMonthAndUserId(user.getId()));
        model.addAttribute("valueAnIncAttr", anotherIncomeServiceJpaImpl.getSumValueAnIncByDateMonthAndUserId(user.getId()));

        model.addAttribute("valueInvSavAttr", investmentsSavingServiceJpaImpl.getSumValueInvSavByDateMonthAndUserId(user.getId()));
        model.addAttribute("valuePropSavAttr", propertyCapitalSavingServiceJpaImpl.getSumValuePropSavByDateMonthAndUserId(user.getId()));
        model.addAttribute("valueLiqSavAttr", liquidCapitalSavingServiceJpaImpl.getSumValueLiqSavByDateMonthAndUserId(user.getId()));

        model.addAttribute("costCafeAttr", cafeServiceJpaImpl.getSumCostCafeByDateMonthAndUserId(user.getId()));
        model.addAttribute("costHealthAttr", healthServiceJpaImpl.getSumCostHealthByDateMonthAndUserId(user.getId()));
        model.addAttribute("costLeisureAttr", leisureServiceJpaImpl.getSumCostLeisureByDateMonthAndUserId(user.getId()));
        model.addAttribute("costProductAttr", productServiceJpaImpl.getSumCostProductByDateMonthAndUserId(user.getId()));
        model.addAttribute("costTransportAttr", transportServiceJpaImpl.getSumCostTransportByDateMonthAndUserId(user.getId()));
        model.addAttribute("costOthersAttr", othersServiceJpaImpl.getSumCostOthersByDateMonthAndUserId(user.getId()));
        return "analytics";
    }
}
