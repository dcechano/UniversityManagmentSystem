package com.example.ums.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class BaseController {

    @RequestMapping
    public String home(Model model) {
        List<String> list = List.of("String 1",
                "String 2",
                "String 3");

        model.addAttribute("list", list);

        return "home";
    }

}
