package com.dvleo.springboot_demo.controller;

import com.dvleo.springboot_demo.form.SignupForm;
import com.dvleo.springboot_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String resSignupView(@ModelAttribute("signupForm") SignupForm singnupform){
        return "signup";
    }

    @PostMapping("/signup")
    public String saveSignupForm(SignupForm singnupform){
        String encodedPassword = passwordEncoder.encode(singnupform.getPassword());
        userRepository.insert(singnupform.getEmail(), encodedPassword);
        return "redirect: /";
    }
}
