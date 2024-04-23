package com.dev.usersweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("message", "Welcome");
        return "home";
    }
}
