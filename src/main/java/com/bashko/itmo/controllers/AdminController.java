package com.bashko.itmo.controllers;

import com.bashko.itmo.repositories.RoleRepository;
import com.bashko.itmo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class AdminController {

    private UserServiceImpl userServiceImpl;
    private RoleRepository roleRepository;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl,RoleRepository roleRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin")
    public String registration(Model model) {
        model.addAttribute("userList",userServiceImpl.findAllByRolesNotContaining(roleRepository.findByName("ROLE_ADMIN")));
        return "admin";
    }

    @GetMapping(value = "/admin/removeUser/{id}")
    public String removeByIdCafe(@PathVariable(value = "id") Long idUser) {
        userServiceImpl.deleteById(idUser);
        return "redirect:/admin";
    }
}
