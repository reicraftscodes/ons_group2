package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.dto.account.ChangePasswordDto;
import com.ons.group2.ons_client_project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/Account")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/ChangePassword")
    public String changePassword(@Valid ChangePasswordDto changePasswordDto) {

        userService.changePassword(changePasswordDto.user, changePasswordDto.newPassword);

        return "redirect:/profiles/" + changePasswordDto.user.getId();
    }
}
