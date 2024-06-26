package com.dvleo.springboot_demo.controller;

import com.dvleo.springboot_demo.entity.UserEntity;
import com.dvleo.springboot_demo.form.SignupForm;
import com.dvleo.springboot_demo.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String resSignupView(@ModelAttribute("signupForm") SignupForm signupForm, Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            // DBからユーザーを検索
            Optional<UserEntity> userOptional = Optional.ofNullable(userRepository.findByEmail(authentication.getName()));
            if (!userOptional.isPresent()) {
                // ユーザーが存在しない場合は、新規登録画面にリダイレクト
                return "redirect:/signup";
            }
            return "redirect:/";
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String saveSignupForm(@Valid SignupForm signupForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response){

        if(bindingResult.hasErrors()){
            return "signup";
        }

        String encodedPassword = passwordEncoder.encode(signupForm.getPassword());
        userRepository.insert(signupForm.getEmail(), encodedPassword);
        /*
        Spring Security有効後は、DBに登録した未ハッシュ化パスワードを使ったログインができなくなる。
        ログインを試みると、デフォルトのエラーメッセージ（`Invalid email or password.`）が出る。
        */


        // ユーザー登録後の自動ログイン：セキュリティ面に問題あり
        try {
            request.login(signupForm.getEmail(), signupForm.getPassword());
            return "redirect:/";
        } catch (ServletException e) {
            return "redirect:/login";
        }

    }
}
