package com.dvleo.springboot_demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String viewHome(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("email", email);
        return "index";
    }
}
