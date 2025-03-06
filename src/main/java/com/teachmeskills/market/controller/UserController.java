package com.teachmeskills.market.controller;

import com.teachmeskills.market.exception.UserNotFoundException;
import com.teachmeskills.market.model.User;
import com.teachmeskills.market.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/register")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(HttpServletRequest request, Model model) {
        String login = (String) request.getSession().getAttribute("currentUser");
        if (login == null) {
            return "redirect:/auth/login";
        }

        try {
            List<User> users = userService.getAllUsers(login);
            if (!users.isEmpty()) {
                model.addAttribute("users", users);
                return "users";
            } else {
                throw new UserNotFoundException("No users found for login -> " + login);
            }
        } catch (UserNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}