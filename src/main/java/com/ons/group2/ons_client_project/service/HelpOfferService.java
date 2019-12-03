package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.dto.help_offer.NewHelpOfferDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface HelpOfferService {
    HelpOffer save(HelpOffer helpOffer);
    Optional<HelpOffer> findById(Long id);
    List<HelpOffer> getAllOffers();
}
