package com.dev.usersweb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Value(value = "${page.login.title}")
    private String title;

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("title", title);
        return "login";
    }
}
