package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.dto.help_offer.NewHelpOfferDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HelpOfferService {
    HelpOffer save(HelpOffer helpOffer);
    Optional<HelpOffer> findById(Long id);
}
