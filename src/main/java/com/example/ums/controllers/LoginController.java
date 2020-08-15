package com.example.ums.controllers;

import com.example.ums.entities.Role;
import com.example.ums.entities.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping
    public String login(Model model) {
        return "login";
    }


}