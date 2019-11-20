package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;

import java.util.Optional;

public interface UserSkillService {

    Optional<UserSkill> getById(Integer id);

    UserSkill save(NewSkillDto newSkillDto);
}
