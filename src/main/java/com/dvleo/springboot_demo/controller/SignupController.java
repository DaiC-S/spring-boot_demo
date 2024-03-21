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
    public String resSignupView(@ModelAttribute("signupForm") SignupForm signupform){
        return "signup";
    }

    @PostMapping("/signup")
    public String saveSignupForm(SignupForm signupform){
        String encodedPassword = passwordEncoder.encode(signupform.getPassword());
        userRepository.insert(signupform.getEmail(), encodedPassword);
        return "redirect:/";
    }
}
