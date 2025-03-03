package com.teachmeskills.market.controller;

import com.teachmeskills.market.exception.AuthenticationException;
import com.teachmeskills.market.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthService authService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            Model model) {

        try {
            boolean isAuthenticated = authService.isAuthenticateUser(login, password);
            if (isAuthenticated) {
                return "redirect:/security/registration";
            }
        } catch (AuthenticationException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }

        return "login";
    }
}