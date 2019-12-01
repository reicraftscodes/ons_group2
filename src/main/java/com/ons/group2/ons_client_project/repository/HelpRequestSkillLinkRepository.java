package com.ons.group2.ons_client_project.repository;


import com.ons.group2.ons_client_project.model.HelpRequestSkillLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpRequestSkillLinkRepository extends JpaRepository<HelpRequestSkillLink, Integer> {
    HelpRequestSkillLink save(HelpRequestSkillLink helpRequestSkillLink);
}
