package com.ons.group2.ons_client_project.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("content", "dashboard");
        return "admin/index";
    }
}
