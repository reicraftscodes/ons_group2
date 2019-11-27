package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.HelpOfferSkillLink;
import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.help_offer.NewHelpOfferDto;
import com.ons.group2.ons_client_project.service.HelpOfferService;
import com.ons.group2.ons_client_project.service.HelpOfferSkillLinkService;
import com.ons.group2.ons_client_project.service.UserService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.midi.SysexMessage;
import javax.validation.Valid;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class HelpOfferController {

    private HelpOfferService helpOfferService;
    private HelpOfferSkillLinkService helpOfferSkillLinkService;
    private UserSkillService userSkillService;

    public HelpOfferController(HelpOfferService helpOfferService, HelpOfferSkillLinkService helpOfferSkillLinkService,UserSkillService userSkillService) {
        this.helpOfferService = helpOfferService;
        this.helpOfferSkillLinkService = helpOfferSkillLinkService;
        this.userSkillService = userSkillService;
    }

    @GetMapping("/createOffer")
    public String createOffer(Model model) {
        String currentUserName;
        User currentUser = null;

        //TODO: UNCOMMENT CURRENT USER GRAB METHOD AND REPLACE HARD CODED USER ID FROM CALL TO USER SKILL SERVICE TO GET USER SKILLS

        //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // get the current logged in users name and then find the user object with corresponding username
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            currentUserName = authentication.getName();
//            currentUser = userService.getUserByUsername(currentUserName);
//        }

        // add all skills the user has selected on their profile to model
        List<UserSkill> userSkills = userSkillService.getAllForUser(1);
        model.addAttribute("userSkills", userSkills);

        // add data transfer object to model to be able to parse to submit method
        model.addAttribute("NewHelpOfferDto", new NewHelpOfferDto());
        return "help_offer_and_help_requests/t_help_offer_form";
    }


    @PostMapping("/submitOffer")
    public ModelAndView submitOffer(@Valid @ModelAttribute NewHelpOfferDto newHelpOfferDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            // add all skills the user has selected on their profile to model
            List<UserSkill> userSkills = userSkillService.getAllForUser(1);
            model.addAttribute("userSkills", userSkills);

            // add data transfer object to model to be able to parse to submit method
            model.addAttribute("NewHelpOfferDto", new NewHelpOfferDto());

            return new ModelAndView("help_offer_and_help_requests/t_help_offer_form");

        }



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

        // save help offer
        HelpOffer newOffer = new HelpOffer(null,dummyUser,date,newHelpOfferDto.getTitle(),newHelpOfferDto.getDescription(),newHelpOfferDto.getMethodOfContact()); // save offer to database
        HelpOffer savedOffer = helpOfferService.save(newOffer);
        System.out.println(savedOffer + "AAA");
        Long savedOfferId =  savedOffer.getId();

        // save tagged skills
        System.out.println(newHelpOfferDto.getTaggedSkills().isEmpty() + "TEST");
        for(UserSkill userSkill:newHelpOfferDto.getTaggedSkills()){
            helpOfferSkillLinkService.save(new HelpOfferSkillLink(null,userSkill,savedOffer));
        }

        return new ModelAndView("redirect:/helpOffer/"+savedOfferId);
    }

    @GetMapping("/helpOffer/{id}")
    public String helpOffer(@PathVariable Long id, Model model){

        Optional<HelpOffer> helpOffer = helpOfferService.findById(id);
        if(helpOffer.isPresent()){
            model.addAttribute("offer",helpOffer.get());
        }
        return"help_offer_and_help_requests/t_help_offer";

    }
}
