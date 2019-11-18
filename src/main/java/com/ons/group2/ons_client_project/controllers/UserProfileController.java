package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.repository.UserRepository;
import com.ons.group2.ons_client_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/profiles")
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String profilePage(@PathVariable("id") Integer id, Model model) {

        Optional<User> user = userService.findById(id);

        if(user.isEmpty()) {
            return "404";
        }

        model.addAttribute("userInfo", user.get());

        return "profile_page/t_profile_page";
    }


}
