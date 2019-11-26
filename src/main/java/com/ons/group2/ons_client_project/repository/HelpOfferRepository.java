package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.HelpOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpOfferRepository extends JpaRepository<HelpOffer, Integer> {

    HelpOffer save(HelpOffer helpOffer);
}
