package com.ons.group2.ons_client_project.web.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {

    @GetMapping("/user")
    public String userIndex(Model model) {
        model.addAttribute("content", "dashboard");
        return "user/index";
    }
}
