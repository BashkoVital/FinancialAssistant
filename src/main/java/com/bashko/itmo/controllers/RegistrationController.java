package com.bashko.itmo.controllers;

import com.bashko.itmo.entities.FinAssist;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.services.FinAssistServiceJpaImpl;
import com.bashko.itmo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserServiceImpl userServiceImpl;
    private FinAssistServiceJpaImpl finAssistServiceJpaImpl;

    @Autowired
    public RegistrationController(UserServiceImpl userServiceImpl, FinAssistServiceJpaImpl finAssistServiceJpaImpl) {
        this.userServiceImpl = userServiceImpl;
        this.finAssistServiceJpaImpl = finAssistServiceJpaImpl;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            if (userServiceImpl.existsUserByUsername(userForm.getUsername())) {
                model.addAttribute("passwordError", "Пароли не совпадают");
                model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
                return "registration";
            } else{
                model.addAttribute("passwordError", "Пароли не совпадают");
                return "registration";
            }
        }
        if (!userServiceImpl.save(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        User user = userServiceImpl.findUserLastAdded();
        finAssistServiceJpaImpl.save(new FinAssist("Income", user));
        finAssistServiceJpaImpl.save(new FinAssist("Saving", user));
        finAssistServiceJpaImpl.save(new FinAssist("Expenses", user));

        return "redirect:";
    }

}
