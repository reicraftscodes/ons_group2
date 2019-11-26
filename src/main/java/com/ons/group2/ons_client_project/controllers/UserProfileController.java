package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.dto.account.ChangePasswordDto;
import com.ons.group2.ons_client_project.service.UserService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/Profiles")
@SessionAttributes({"user"})
public class UserProfileController {

    private final UserService userService;
    private final UserSkillService userSkillService;

    public UserProfileController(UserService userService, UserSkillService userSkillService) {
        this.userService = userService;
        this.userSkillService = userSkillService;
    }

    @GetMapping("/test/{id}")
    public String profilePage(@PathVariable("id") Integer id, Model model) {

        Optional<User> user = userService.findById(id);

        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        model.addAttribute("user", user.get());
        model.addAttribute("userSkills", userSkillService.getAllForUser(user.get().getId()));

        return "profile_page/t_profile_page";
    }


}
