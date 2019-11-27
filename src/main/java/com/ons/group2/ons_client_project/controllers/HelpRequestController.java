package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.helpRequest.NewHelpRequestDto;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelpRequestController {

    private UserSkillService userSkillService;

    public HelpRequestController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @GetMapping("/createRequest")
    public String createRequest(Model model){
        String currentUserName;
        User currentUser = null;

        //TODO: UNCOMMENT CURRENT USER GRAB METHOD AND REPLACE HARD CODED USER ID FROM CALL TO USER SKILL SERVICE TO GET USER SKILLS

        //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // get the current logged in users name and then find the user object with corresponding username
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            currentUserName = authentication.getName();
//            currentUser = userService.getUserByUsername(currentUserName);
//        }

        // add all skills the user has selected on their profile to model
        List<UserSkill> userSkills = userSkillService.getAllForUser(1); // replace 1 with actual user id in the future
        model.addAttribute("userSkills", userSkills);

        // add data transfer object to model to be able to parse to submit method
        model.addAttribute("NewHelpRequestDto", new NewHelpRequestDto());
        return "help_offer_and_help_requests/t_help_offer_form";



    }
}
