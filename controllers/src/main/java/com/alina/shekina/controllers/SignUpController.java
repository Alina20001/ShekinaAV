package com.alina.shekina.controllers;

import com.alina.shekina.services.UserService;
import com.alina.shekina.userDetails.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;

import static com.google.common.base.Strings.isNullOrEmpty;

@Controller
public class SignUpController extends ResidenceController {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;


    @Autowired
    public SignUpController(PasswordEncoder passwordEncoder, UserServiceImpl userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "registration";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestParam String email, @RequestParam String password, @RequestParam String country,
                         @RequestParam String name, @RequestParam String phone, @RequestParam String surname) {
        try {
            userService.saveUser(email, password, country, name, phone, surname);
        } catch (Exception e) {

        }
            return "redirect:/hotel";
        }


}
