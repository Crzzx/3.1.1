package com.example.demo.controller;

import com.example.demo.Service.UserServiceImp;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



@Controller
public class UsersController {

    private final UserServiceImp userService;

    @Autowired
    public UsersController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsersList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/user-add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/user-add")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-add";
        }
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "user-update";
        }
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/user-delete")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }

}
