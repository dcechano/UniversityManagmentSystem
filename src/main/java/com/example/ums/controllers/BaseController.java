package com.example.ums.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("user")
@Controller
@RequestMapping("/")
public class BaseController {

    @RequestMapping
    public String home(Model model) {
        return "landing";
    }

    //  TODO Remove? or at least move to a place more appropriate!
    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

}
