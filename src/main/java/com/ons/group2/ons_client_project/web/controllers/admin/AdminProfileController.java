package com.ons.group2.ons_client_project.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminProfileController {

    @GetMapping("/admin/profile")
    public String userProfile(Model model) {
        model.addAttribute("content", "profile");
        return "admin/index";
    }
}
