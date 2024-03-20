package com.dvleo.springboot_demo.config;

import com.dvleo.springboot_demo.entity.UserEntity;
import com.dvleo.springboot_demo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers("/", "/login", "/signup").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        login -> login.loginPage("/login")
                                .usernameParameter("email")
                                .defaultSuccessUrl("/")
                )
                .logout(logout -> logout.logoutSuccessUrl("/login"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return email -> {
            UserEntity user = userRepository.findByEmail(email);
            if(user != null){
                UserDetails userDetails = User.withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();
                return userDetails;
            }
            throw new UsernameNotFoundException("User '" + email + "' not found");
        };
    }
}
