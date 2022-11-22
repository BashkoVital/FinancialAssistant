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
public class ExpensesController {

    private final CafeServiceJpaImpl cafeServiceJpaImpl;
    private final HealthServiceJpaImpl healthServiceJpaImpl;
    private final LeisureServiceJpaImpl leisureServiceJpaImpl;
    private final ProductServiceJpaImpl productServiceJpaImpl;
    private final TransportServiceJpaImpl transportServiceJpaImpl;
    private final OthersServiceJpaImpl othersServiceJpaImpl;

    @Autowired
    public ExpensesController(CafeServiceJpaImpl cafeServiceJpaImpl,
                              HealthServiceJpaImpl healthServiceJpaImpl,
                              LeisureServiceJpaImpl leisureServiceJpaImpl,
                              ProductServiceJpaImpl productServiceJpaImpl,
                              TransportServiceJpaImpl transportServiceJpaImpl,
                              OthersServiceJpaImpl othersServiceJpaImpl) {
        this.cafeServiceJpaImpl = cafeServiceJpaImpl;
        this.healthServiceJpaImpl = healthServiceJpaImpl;
        this.leisureServiceJpaImpl = leisureServiceJpaImpl;
        this.productServiceJpaImpl = productServiceJpaImpl;
        this.transportServiceJpaImpl = transportServiceJpaImpl;
        this.othersServiceJpaImpl = othersServiceJpaImpl;
    }

    @GetMapping(value = "/expenses/allTime")
    public String getExpensesByAllTime(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("cafeList", cafeServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("healthList", healthServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("leisureList", leisureServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("productList", productServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("transportList", transportServiceJpaImpl.findAllByUserId(user.getId()));
        model.addAttribute("othersList", othersServiceJpaImpl.findAllByUserId(user.getId()));
        return "expenses";
    }

    @GetMapping(value = "/expenses/currentMonth")
    public String getExpensesByCurrentMonth(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("cafeList", cafeServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("healthList", healthServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("leisureList", leisureServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("productList", productServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("transportList", transportServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        model.addAttribute("othersList", othersServiceJpaImpl.findAllByDateMonthAndUserId(user.getId()));
        return "expenses";
    }

    @GetMapping(value = "/expenses/removeCafe/{id}")
    public String removeByIdCafe(@PathVariable(value = "id") Long idCafe) {
        cafeServiceJpaImpl.deleteById(idCafe);
        return "redirect:/expenses/currentMonth";
    }

    @GetMapping(value = "/expenses/removeHealth/{id}")
    public String removeByIdHealth(@PathVariable(value = "id") Long idHealth) {
        healthServiceJpaImpl.deleteById(idHealth);
        return "redirect:/expenses/currentMonth";
    }

    @GetMapping(value = "/expenses/removeLeisure/{id}")
    public String removeByIdLeisure(@PathVariable(value = "id") Long idLeisure) {
        leisureServiceJpaImpl.deleteById(idLeisure);
        return "redirect:/expenses/currentMonth";
    }

    @GetMapping(value = "/expenses/removeProduct/{id}")
    public String removeByIdProduct(@PathVariable(value = "id") Long idProduct) {
        productServiceJpaImpl.deleteById(idProduct);
        return "redirect:/expenses/currentMonth";
    }

    @GetMapping(value = "/expenses/removeTransport/{id}")
    public String removeByIdTransport(@PathVariable(value = "id") Long idTransport) {
        transportServiceJpaImpl.deleteById(idTransport);
        return "redirect:/expenses/currentMonth";
    }

    @GetMapping(value = "/expenses/removeOthers/{id}")
    public String removeByIdOthers(@PathVariable(value = "id") Long idOthers) {
        othersServiceJpaImpl.deleteById(idOthers);
        return "redirect:/expenses/currentMonth";
    }

}
