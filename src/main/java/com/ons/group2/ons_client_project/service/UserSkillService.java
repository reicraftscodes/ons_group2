package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.UserSkill;

import java.util.Optional;

public interface UserSkillService {

    Optional<UserSkill> getById(Integer id);

    UserSkill save(UserSkill userSkill);
}
