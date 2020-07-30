package com.example.ums.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    ApplicationContext context;

    public LoginController(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping
    public String login(Model model) {

        String[] names = context.getBeanDefinitionNames();
        model.addAttribute("beans", names);
        return "login";
    }


}
