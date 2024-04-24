package com.dev.usersweb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @Value(value = "${page.home.title}")
    private String title;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("title", title);
        model.addAttribute("message", "Welcome");
        return "home";
    }
}
