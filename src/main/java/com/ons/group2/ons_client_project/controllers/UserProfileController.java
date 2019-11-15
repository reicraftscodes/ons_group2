package com.ons.group2.ons_client_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profiles")
public class UserProfileController {

    @GetMapping("/{id}")
    public String profilePage(@PathVariable("id") String id) {
        return "profile_page/t_profile_page";
    }
}
