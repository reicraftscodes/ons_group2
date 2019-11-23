package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.dto.help_offer.NewHelpOfferDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelpOfferController {

    @GetMapping("/createOffer")
    public String createOffer(Model model){
        model.addAttribute("NewHelpOfferDto", new NewHelpOfferDto());
        return "help_offer_and_help_requests/t_help_offer_form";
    }

    @PostMapping("/submitOffer")
    public String submitOffer(Model model){
        return "placeholder";
    }
}
