package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.repository.HelpOfferRepository;
import com.ons.group2.ons_client_project.service.HelpOfferService;

public class HelpOfferServiceImpl implements HelpOfferService {

    private HelpOfferRepository helpOfferRepository;

    public HelpOfferServiceImpl(HelpOfferRepository helpOfferRepository) {
        this.helpOfferRepository = helpOfferRepository;
    }

    @Override
    public void saveOffer(HelpOffer helpOffer) {
        helpOfferRepository.saveOffer(helpOffer);

    }
}
