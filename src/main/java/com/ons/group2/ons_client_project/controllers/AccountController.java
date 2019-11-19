package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.dto.account.ChangePasswordDto;
import com.ons.group2.ons_client_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/Account")
@SessionAttributes({"user"})
@Slf4j
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/ChangePassword")
    public String changePassword(@ModelAttribute("changePasswordDto") @Valid ChangePasswordDto changePasswordDto,
                                 @SessionAttribute("user") User user,
                                 BindingResult bindingResult,
                                 Model model) {

        log.info("Change Password Dto: " + changePasswordDto);
        log.info("Change Password For user: " + user);

        if(!changePasswordDto.newPassword.equals(changePasswordDto.newPasswordCf)) {
            bindingResult.addError(
                    new FieldError("changePasswordDto", "newPasswordCf", "The two passwords do not match.")
            );
        } else if (!changePasswordDto.oldPassword.equals(user.getPassword())) {
            bindingResult.addError(
                    new FieldError("changePasswordDto", "oldPassword", "Invalid password.")
            );
        }

        userService.changePassword(user, changePasswordDto.newPassword);

        model.addAttribute("changePasswordSuccess", "Password changed!");

        return "profile_page/t_profile_page";
    }
}
