package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.dto.help_offer.NewHelpOfferDto;
import com.ons.group2.ons_client_project.service.HelpOfferService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sound.midi.SysexMessage;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class HelpOfferController {

    private HelpOfferService helpOfferService;

    public HelpOfferController(HelpOfferService helpOfferService) {
        this.helpOfferService = helpOfferService;
    }

    @GetMapping("/createOffer")
    public String createOffer(Model model){
        model.addAttribute("NewHelpOfferDto", new NewHelpOfferDto());
        return "help_offer_and_help_requests/t_help_offer_form";
    }

    @PostMapping("/submitOffer")
    public String submitOffer(Model model, @ModelAttribute NewHelpOfferDto newHelpOfferDto){
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); // get the current date of posting

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // get the current logged in users name
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println("USERNAME"+currentUserName);
        }




        HelpOffer newOffer = new HelpOffer(null,null,date,newHelpOfferDto.getTitle(),newHelpOfferDto.getDescription(),newHelpOfferDto.getMethodOfContact());
        helpOfferService.save(newOffer);

        return "placeholder";
    }
}
