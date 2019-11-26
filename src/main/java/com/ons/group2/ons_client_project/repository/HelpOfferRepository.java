package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.HelpOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HelpOfferRepository extends JpaRepository<HelpOffer, Integer> {

    HelpOffer save(HelpOffer helpOffer);

    Optional<HelpOffer> findById(Long id);
}
