package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.HelpRequest;
import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.helpRequest.NewHelpRequestDto;
import com.ons.group2.ons_client_project.service.HelpRequestService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;

@Controller
public class HelpRequestController {

    private UserSkillService userSkillService;

    private HelpRequestService helpRequestService;

    public HelpRequestController(UserSkillService userSkillService,HelpRequestService helpRequestService) {
        this.userSkillService = userSkillService;
        this.helpRequestService = helpRequestService;
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
        return "help_offer_and_help_requests/t_help_request_form";
    }

    @PostMapping("/submitRequest")
    public ModelAndView submitOffer(Model model, @ModelAttribute NewHelpRequestDto newHelpRequestDto){
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); // get the current date of posting
        String currentUserName;
        User currentUser = null;

        User dummyUser = new User(1,"testUser","testUser@gmail.com","testPassword","www.google.com");

        //TODO: CHANGE FROM DUMMY USER FOR TESTING PURPOSES ONCE LOGIN HAS BEEN MERGED TO MASTER TO ALLOW ACTUAL USER TO BE SAVED INSTEAD OF DUMMY USER
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // get the current logged in users name and then find the user object with corresponding username
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            currentUserName = authentication.getName();
//            currentUser = userService.getUserByUsername(currentUserName);
//        }

        // save help Request
        HelpRequest newRequest = new HelpRequest(null,dummyUser,date,newHelpRequestDto.getTitle(),newHelpRequestDto.getDescription(),newHelpRequestDto.getMethodOfContact());  // convert helpRequestDto to helpRequest model
        HelpRequest savedRequest = helpRequestService.save(newRequest); // returned helpRequest that's saved to the db containing PK
        Long savedOfferId =  savedRequest.getId(); //

        // save tagged skills
        for(UserSkill userSkill:newHelpRequestDto.getTaggedSkills()){
            helpRequestSkillLinkService.save(new HelpRequestSkillLink(null,userSkill,savedRequest));
        }

        return new ModelAndView("redirect:/helpOffer/"+savedOfferId);
    }

}
