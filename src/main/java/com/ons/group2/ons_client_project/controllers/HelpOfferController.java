package com.ons.group2.ons_client_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelpOfferController {

    @PostMapping("/createOffer")
    public String createOffer(Model model){
        return "t_help_offer_form";
    }
}
