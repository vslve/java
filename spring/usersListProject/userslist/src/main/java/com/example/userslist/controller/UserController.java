package com.example.userslist.controller;

import com.example.userslist.model.User;
import com.example.userslist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String startPage() {
        return "start_page";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users_list";
    }

    @GetMapping("/users/create")
    public String createUserForm(User user) {
        return "user_create";
    }

    @PostMapping("/create")
    public String createUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user_create";
        }

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user_update";
    }

    @PatchMapping("/update")
    public String updateUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user_update";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
