package com.ons.group2.ons_client_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/Categories/Manage")
    public String managePage() {
        return "category/t_category_management";
    }
}
