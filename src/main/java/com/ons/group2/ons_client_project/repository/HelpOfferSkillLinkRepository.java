package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.HelpOfferSkillLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpOfferSkillLinkRepository extends JpaRepository<HelpOfferSkillLink, Integer> {
    HelpOfferSkillLink save(HelpOfferSkillLink helpOfferSkillLink);
}
