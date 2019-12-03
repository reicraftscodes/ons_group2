package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.repository.HelpOfferRepository;
import com.ons.group2.ons_client_project.service.HelpOfferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpOfferServiceImpl implements HelpOfferService {

    private HelpOfferRepository helpOfferRepository;

    public HelpOfferServiceImpl(HelpOfferRepository helpOfferRepository) {
        this.helpOfferRepository = helpOfferRepository;
    }

    @Override
    public HelpOffer save(HelpOffer helpOffer) {
        return helpOfferRepository.save(helpOffer);

    }

    @Override
    public Optional<HelpOffer> findById(Long id) {
        return helpOfferRepository.findById(id);
    }

    @Override
    public List<HelpOffer> getAllOffers() {
        return helpOfferRepository.findAll();
    }
}
