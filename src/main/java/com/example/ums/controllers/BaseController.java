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
        return "landing";
    }

}
