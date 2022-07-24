package com.event.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping
    public String getWelcome() {
        return "welcome";
    }

    @GetMapping("welcome")
    public String getWelcome1(Model model) {
        model.addAttribute("welcomeText", "Welcome from /welcome");
        return "welcome";
    }

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("app")
    public String getApp() {
        return "app";
//        return "redirect:http://localhost:3000";
    }

}
