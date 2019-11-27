package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.HelpOfferSkillLink;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.repository.HelpOfferSkillLinkRepository;
import com.ons.group2.ons_client_project.service.HelpOfferSkillLinkService;
import org.springframework.stereotype.Service;

@Service
public class HelpOfferSkillLinkServiceImpl implements HelpOfferSkillLinkService {

    HelpOfferSkillLinkRepository helpOfferSkillLinkRepository;

    public HelpOfferSkillLinkServiceImpl(HelpOfferSkillLinkRepository helpOfferSkillLinkRepository) {
        this.helpOfferSkillLinkRepository = helpOfferSkillLinkRepository;
    }

    @Override
    public HelpOfferSkillLink save(HelpOfferSkillLink helpOfferSkillLink) {
        return helpOfferSkillLinkRepository.save(helpOfferSkillLink);
    }
}
