package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.HelpRequestSkillLink;
import com.ons.group2.ons_client_project.repository.HelpRequestSkillLinkRepository;
import com.ons.group2.ons_client_project.service.HelpRequestSkillLinkService;
import org.springframework.stereotype.Service;

@Service
public class HelpRequestSkillLinkImpl implements HelpRequestSkillLinkService {
    HelpRequestSkillLinkRepository helpRequestSkillLinkRepository;

    public HelpRequestSkillLinkImpl(HelpRequestSkillLinkRepository helpRequestSkillLinkRepository) {
        this.helpRequestSkillLinkRepository = helpRequestSkillLinkRepository;
    }

    @Override
    public HelpRequestSkillLink save(HelpRequestSkillLink helpOfferSkillLink) {
        return helpRequestSkillLinkRepository.save(helpOfferSkillLink);
    }

}
