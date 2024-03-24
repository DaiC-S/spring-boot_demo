package com.dvleo.springboot_demo.controller;

import com.dvleo.springboot_demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserdetailController {
    private final UserRepository userRepository;

    @GetMapping("/users/{id}")
    public String showUserDetail(@PathVariable long id, Model model){
        var user = userRepository.findById(id);

        if (user == null) {
            return "redirect/";
        }

        model.addAttribute("user", user);
        return "userdetail";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable long id, HttpServletRequest request, HttpServletResponse response){
        userRepository.deleteById(id);
        // 明示的にログアウト処理を行う
        new SecurityContextLogoutHandler().logout(request, response, null);
        return "redirect:/signup";
    }
}
