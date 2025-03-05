package com.teachmeskills.market.controller;

import com.teachmeskills.market.dto.RegistrationRequestDto;
import com.teachmeskills.market.services.RegistrationSecurityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/security")
public class RegistrationSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationSecurityController.class);
    public RegistrationSecurityService securityService;

    @Autowired
    public RegistrationSecurityController(RegistrationSecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationRequestDto", new RegistrationRequestDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid RegistrationRequestDto requestDto,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                logger.error("Validation error: {}", error.getDefaultMessage());
            }
            return "registration";
        }

        String fullTelephoneNumber = requestDto.getOperator() + requestDto.getTelephoneNumber();
        requestDto.setTelephoneNumber(fullTelephoneNumber);

        boolean isRegistered = securityService.registrationNewUser (
                requestDto.getFirstname(),
                requestDto.getSecondName(),
                requestDto.getAge(),
                requestDto.getEmail(),
                requestDto.getSex(),
                requestDto.getTelephoneNumber(),
                requestDto.getLogin(),
                requestDto.getPassword()
        );

        if (isRegistered) {
            return "registrationSuccess";
        } else {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "registration";
        }
    }
}