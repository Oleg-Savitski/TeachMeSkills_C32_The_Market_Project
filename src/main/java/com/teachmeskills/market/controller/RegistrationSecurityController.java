package com.teachmeskills.market.controller;

import com.teachmeskills.market.services.RegistrationSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/security")
public class RegistrationSecurityController {

    public RegistrationSecurityService securityService;

    @Autowired
    public RegistrationSecurityController(RegistrationSecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @RequestParam("firstname") String firstname,
            @RequestParam("secondName") String secondName,
            @RequestParam("age") Integer age,
            @RequestParam("email") String email,
            @RequestParam("sex") String sex,
            @RequestParam("telephoneNumber") String telephoneNumber,
            @RequestParam("login") String login,
            @RequestParam("password") String password
    ) {
        boolean isRegistered = securityService.registration(firstname, secondName, age, email, sex, telephoneNumber, login, password);
        return isRegistered ? "registrationSuccess" : "registrationFailed";
    }
}