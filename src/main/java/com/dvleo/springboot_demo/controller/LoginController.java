package com.dvleo.springboot_demo.controller;

import com.dvleo.springboot_demo.form.LoginForm;
import com.dvleo.springboot_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;

    @GetMapping("/login")
    public String resLoginView(@ModelAttribute("loginForm") LoginForm loginForm, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

}
