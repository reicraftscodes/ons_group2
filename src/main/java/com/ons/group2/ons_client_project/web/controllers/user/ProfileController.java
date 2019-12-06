package com.ons.group2.ons_client_project.web.controllers.user;

import com.ons.group2.ons_client_project.model.dto.account.SafeUserDetails;
import com.ons.group2.ons_client_project.model.dto.account.UpdateUserInfoDto;
import com.ons.group2.ons_client_project.service.UserService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class ProfileController {

    private final UserService userService;
    private final UserSkillService userSkillService;

    public ProfileController(UserService userService, UserSkillService userSkillService) {
        this.userService = userService;
        this.userSkillService = userSkillService;
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model) {
        model.addAttribute("content", "profile");
        return "user/index";
    }

    @GetMapping("/user/{id}")
    public String otherUserProfile(@PathVariable Long id, Model model) {
        var userOptional = userService.findById(id);

        if(userOptional.isEmpty()) return "404";

        var user = userOptional.get();

        model.addAttribute("content", "userDetails");
        model.addAttribute("profile",
                new SafeUserDetails(
                    user.getFullName(),
                    user.getEmail(),
                    user.getProfileUrl()
                )
        );
        model.addAttribute("userSkills", userSkillService.getAllForUser(user.getId()));

        return "user/index";
    }

    @PutMapping("/user/update")
    public String updateProfile(@RequestBody @Valid UpdateUserInfoDto userInfoDto) {



        return "/user/index";
    }
}
