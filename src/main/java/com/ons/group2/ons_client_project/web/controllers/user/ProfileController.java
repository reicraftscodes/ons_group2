package com.ons.group2.ons_client_project.web.controllers.user;

import com.ons.group2.ons_client_project.model.dto.account.SafeUserDetails;
import com.ons.group2.ons_client_project.model.dto.account.UpdateUserInfoDto;
import com.ons.group2.ons_client_project.security.UserPrincipal;
import com.ons.group2.ons_client_project.service.UserService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import com.ons.group2.ons_client_project.utils.UserUtils;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@Slf4j
public class ProfileController {

    private final UserService userService;
    private final UserSkillService userSkillService;

    public ProfileController(UserService userService, UserSkillService userSkillService) {
        this.userService = userService;
        this.userSkillService = userSkillService;
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model, Authentication auth) {

        var user = UserUtils.getUserFromAuth(auth);

        var formPreFilled = new UpdateUserInfoDto(null, user.getFirstName(), user.getLastName(), user.getEmail());

        model.addAttribute("updateFrom", formPreFilled);
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

    @PostMapping("/user/update")
    public String updateProfile(
            @Valid UpdateUserInfoDto userInfoDto,
            Model model,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            Authentication auth) {

        if(bindingResult.hasErrors()) {
            return "user/index";
        }

        var user = UserUtils.getUserFromAuth(auth);

        // Update the user info for the logged in user no matter what
        userInfoDto.setUserId(user.getId());

        try {
            user = userService.updateUser(userInfoDto);

        } catch (IllegalArgumentException e) {
            log.error(String.format("Error updating user profile: %s", e.getMessage()));
        } catch (NotFoundException e) {
            log.error(e.getMessage());
        } finally {
            ((UserPrincipal) auth.getPrincipal()).setUser(user);
            SecurityContextHolder.getContext().setAuthentication(auth); // refresh logged in user details

            var formPreFilled = new UpdateUserInfoDto(null, user.getFirstName(),
                    user.getLastName(), user.getEmail());

            model.addAttribute("updateFrom", formPreFilled);
        }



        model.addAttribute("content", "profile");

        return "user/index";
    }
}
