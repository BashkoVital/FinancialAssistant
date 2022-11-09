package com.bashko.itmo.controllers;

import com.bashko.itmo.entities.*;
import com.bashko.itmo.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonalAreaController {

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
    public PersonalAreaController(SalaryIncomeServiceJpaImpl salaryIncomeServiceJpaImpl,
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

    //===============================================================================================================

    @GetMapping(value = "/personalArea.html")
    public String getFinAssistant(Model model) {
        model.addAttribute("salaryIncome", new SalaryIncome());
        model.addAttribute("investmentIncome", new InvestmentIncome());
        model.addAttribute("anotherIncome", new AnotherIncome());

        model.addAttribute("investmentsSaving", new InvestmentsSaving());
        model.addAttribute("propertyCapitalSaving", new PropertyCapitalSaving());
        model.addAttribute("liquidCapitalSaving", new LiquidCapitalSaving());

        model.addAttribute("cafe", new Cafe());
        model.addAttribute("health", new Health());
        model.addAttribute("leisure", new Leisure());
        model.addAttribute("product", new Product());
        model.addAttribute("transport", new Transport());
        model.addAttribute("others", new Others());
        return "personalArea";
    }

    @PostMapping(value = "/personalArea.html")
    public String saveFinAssistant(@ModelAttribute("salaryIncome") SalaryIncome salaryIncome,
                                  @ModelAttribute("investmentIncome") InvestmentIncome investmentIncome,
                                  @ModelAttribute("anotherIncome") AnotherIncome anotherIncome,

                                  @ModelAttribute("investmentsSaving") InvestmentsSaving investmentsSaving,
                                  @ModelAttribute("propertyCapitalSaving") PropertyCapitalSaving propertyCapitalSaving,
                                  @ModelAttribute("liquidCapitalSaving") LiquidCapitalSaving liquidCapitalSaving,

                                  @ModelAttribute("cafe") Cafe cafe,
                                  @ModelAttribute("health") Health health,
                                  @ModelAttribute("leisure") Leisure leisure,
                                  @ModelAttribute("product") Product product,
                                  @ModelAttribute("transport") Transport transport,
                                  @ModelAttribute("others") Others others,

                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "personalArea";
        }
        if (((SalaryIncome) model.getAttribute("salaryIncome")).getValueSalary() != 0)
            salaryIncomeServiceJpaImpl.save(salaryIncome);
        if (((InvestmentIncome) model.getAttribute("investmentIncome")).getValueInvInc() != 0)
            investmentIncomeServiceJpaImpl.save(investmentIncome);
        if (((AnotherIncome) model.getAttribute("anotherIncome")).getValueAnInc() != 0)
            anotherIncomeServiceJpaImpl.save(anotherIncome);

        if (((InvestmentsSaving) model.getAttribute("investmentsSaving")).getValueInvSav() != 0)
            investmentsSavingServiceJpaImpl.save(investmentsSaving);
        else if (((PropertyCapitalSaving) model.getAttribute("propertyCapitalSaving")).getValuePropSav() != 0)
            propertyCapitalSavingServiceJpaImpl.save(propertyCapitalSaving);
        else if (((LiquidCapitalSaving) model.getAttribute("liquidCapitalSaving")).getValueLiqSav() != 0)
            liquidCapitalSavingServiceJpaImpl.save(liquidCapitalSaving);

        else if (((Cafe) model.getAttribute("cafe")).getCostCafe() != 0)
            cafeServiceJpaImpl.save(cafe);
        else if (((Health) model.getAttribute("health")).getCostHealth() != 0)
            healthServiceJpaImpl.save(health);
        else if (((Leisure) model.getAttribute("leisure")).getCostLeisure() != 0)
            leisureServiceJpaImpl.save(leisure);
        else if (((Product) model.getAttribute("product")).getCostProduct() != 0)
            productServiceJpaImpl.save(product);
        else if (((Transport) model.getAttribute("transport")).getCostTransport() != 0)
            transportServiceJpaImpl.save(transport);
        else if (((Others) model.getAttribute("others")).getCostOthers() != 0)
            othersServiceJpaImpl.save(others);

        return "redirect:/personalArea.html";
    }

    //===============================================================================================================


    //==============================================================================================================


    //================================================================================================================


    //===============================================================================================================
}
