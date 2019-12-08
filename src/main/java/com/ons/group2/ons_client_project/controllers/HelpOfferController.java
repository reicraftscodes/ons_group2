package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.HelpOfferSkillLink;
import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.help_offer.NewHelpOfferDto;
import com.ons.group2.ons_client_project.security.UserPrincipal;
import com.ons.group2.ons_client_project.service.HelpOfferService;
import com.ons.group2.ons_client_project.service.HelpOfferSkillLinkService;
import com.ons.group2.ons_client_project.service.UserService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class HelpOfferController {

    private HelpOfferService helpOfferService;
    private HelpOfferSkillLinkService helpOfferSkillLinkService;
    private UserSkillService userSkillService;
    private UserService userService;

    public HelpOfferController(HelpOfferService helpOfferService, HelpOfferSkillLinkService helpOfferSkillLinkService,UserSkillService userSkillService,UserService userService) {
        this.helpOfferService = helpOfferService;
        this.helpOfferSkillLinkService = helpOfferSkillLinkService;
        this.userSkillService = userSkillService;
    }

    @GetMapping("/createOffer")
    public String createOffer(Model model, Authentication authentication) {
        // add all skills the user has selected on their profile to model
        List<UserSkill> userSkills = userSkillService.getAllForUser(getCurrentUser(authentication).getId());
        model.addAttribute("userSkills", userSkills);

        // add data transfer object to model to be able to parse to submit method
        model.addAttribute("newHelpOfferDto", new NewHelpOfferDto());
        return "help_offer_and_help_requests/t_help_offer_form";
    }


    @PostMapping("/submitOffer")
    public ModelAndView submitOffer(@ModelAttribute("NewHelpOfferDto") @Valid NewHelpOfferDto newHelpOfferDto, BindingResult bindingResult, Model model,Authentication authentication){
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); // get the current date of posting

        if(bindingResult.hasErrors()){
            log.error(bindingResult.toString());
            log.error("Donation Form has binding errors");

            // add all skills the user has selected on their profile to model
            List<UserSkill> userSkills = userSkillService.getAllForUser(getCurrentUser(authentication).getId());
            model.addAttribute("userSkills", userSkills);

            model.addAttribute("newHelpOfferDto", new NewHelpOfferDto());
            return new ModelAndView("help_offer_and_help_requests/t_help_offer_form");

        }


        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); // get the current date of posting



        // save help offer
        HelpOffer newOffer = new HelpOffer(null,getCurrentUser(authentication),date,newHelpOfferDto.getTitle(),newHelpOfferDto.getDescription(),getCurrentUser(authentication).getEmail()); // save offer to database
        HelpOffer savedOffer = helpOfferService.save(newOffer);
        Long savedOfferId =  savedOffer.getId();

        // save tagged skills
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

    public User getCurrentUser(Authentication authentication){
        var principal = (UserPrincipal)authentication.getPrincipal();
        return principal.getUser();
    }
}
