package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.HelpRequest;
import com.ons.group2.ons_client_project.model.HelpRequestSkillLink;
import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.helpRequest.NewHelpRequestDto;
import com.ons.group2.ons_client_project.security.UserPrincipal;
import com.ons.group2.ons_client_project.service.HelpRequestService;
import com.ons.group2.ons_client_project.service.HelpRequestSkillLinkService;
import com.ons.group2.ons_client_project.service.UserService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class HelpRequestController {

    private UserSkillService userSkillService;
    private HelpRequestService helpRequestService;
    private HelpRequestSkillLinkService helpRequestSkillLinkService;
    private UserService userService;

    public HelpRequestController(UserSkillService userSkillService,HelpRequestService helpRequestService,HelpRequestSkillLinkService helpRequestSkillLinkService,UserService userService) {
        this.userSkillService = userSkillService;
        this.helpRequestService = helpRequestService;
        this.helpRequestSkillLinkService = helpRequestSkillLinkService;
        this.userService = userService;
    }

    @GetMapping("/createRequest")
    public String createRequest(Model model,Authentication authentication){

        // add all skills the user has selected on their profile to model
        List<UserSkill> userSkills = userSkillService.getAllForUser(getCurrentUser(authentication).getId());
        model.addAttribute("userSkills", userSkills);

        // add data transfer object to model to be able to parse to submit method
        model.addAttribute("newHelpRequestDto", new NewHelpRequestDto());
        return "help_offer_and_help_requests/t_help_request_form";
    }

    @PostMapping("/submitRequest")
    public ModelAndView submitOffer(@Valid @ModelAttribute NewHelpRequestDto newHelpRequestDto, BindingResult bindingResult, Model model,Authentication authentication){
        if (bindingResult.hasErrors()) {

            // add all skills the user has selected on their profile to model
            List<UserSkill> userSkills = userSkillService.getAllForUser(getCurrentUser(authentication).getId()); // replace 1 with actual user id in the future
            model.addAttribute("userSkills", userSkills);
            return new ModelAndView("help_offer_and_help_requests/t_help_request_form");
        }

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); // get the current date of posting

        // save help Request
        HelpRequest newRequest = new HelpRequest(null,getCurrentUser(authentication),date,newHelpRequestDto.getTitle(),newHelpRequestDto.getDescription(),getCurrentUser(authentication).getEmail());  // convert helpRequestDto to helpRequest model
        HelpRequest savedRequest = helpRequestService.save(newRequest); // returned helpRequest that's saved to the db containing PK
        Long savedOfferId =  savedRequest.getId(); //

        // save tagged skills
        for(UserSkill userSkill:newHelpRequestDto.getTaggedSkills()){
            helpRequestSkillLinkService.save(new HelpRequestSkillLink(null,userSkill,savedRequest));
        }

        return new ModelAndView("redirect:/helpRequest/"+savedOfferId);
    }

    @GetMapping("/helpRequest/{id}")
    public String helpRequest(Model model,@PathVariable Long id){

        Optional<HelpRequest> helpRequest = helpRequestService.findById(id);
        if(helpRequest.isPresent()){
            model.addAttribute("request",helpRequest.get());
        }
        return"help_offer_and_help_requests/t_help_request";

    }

    public User getCurrentUser(Authentication authentication){
        var principal = (UserPrincipal)authentication.getPrincipal();
        return principal.getUser();
    }


}
