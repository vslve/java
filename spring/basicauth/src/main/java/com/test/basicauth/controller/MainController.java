package com.test.basicauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping
    public String toWelcomePage() {
        return "redirect:/auth/welcome";
    }

    @GetMapping("/main")
    public String getMain() {
        return "main";
    }
}
