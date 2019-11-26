package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.dto.help_offer.NewHelpOfferDto;
import org.springframework.stereotype.Service;

@Service
public interface HelpOfferService {
    void save(HelpOffer helpOffer);
}
