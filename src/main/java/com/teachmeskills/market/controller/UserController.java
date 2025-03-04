package com.teachmeskills.market.controller;

import com.teachmeskills.market.model.User;
import com.teachmeskills.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user, Model model) {
        boolean isSaved = userService.saveUser(user);
        if (isSaved) {
            model.addAttribute("message", "User saved successfully!");
        } else {
            model.addAttribute("message", "Failed to save user.");
        }
        return "redirect:/users";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit-user";
        } else {
            model.addAttribute("message", "User not found.");
            return "redirect:/users";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user, Model model) {
        user.setId(id);
        boolean isUpdated = userService.updateUser(user);
        if (isUpdated) {
            model.addAttribute("message", "User updated successfully!");
        } else {
            model.addAttribute("message", "Failed to update user.");
        }
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            model.addAttribute("message", "User deleted successfully!");
        } else {
            model.addAttribute("message", "Failed to delete user.");
        }
        return "redirect:/users";
    }
}