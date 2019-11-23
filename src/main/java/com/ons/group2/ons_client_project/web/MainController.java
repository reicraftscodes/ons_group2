package com.ons.group2.ons_client_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "registration";
    }

    @GetMapping("/forgotpass")
    public String forgotpass(Model model) {
        return "forgotpass";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

//    Admin - responsible for updating / deleting stuff on the website.
    @GetMapping("/admin")
    public String adminIndex() {
        return "admin/index";
    }

}