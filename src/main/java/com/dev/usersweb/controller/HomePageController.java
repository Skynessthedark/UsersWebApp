package com.dev.usersweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("message", "Welcome");
        return "home";
    }
}
