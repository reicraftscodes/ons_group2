package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.repository.UserSkillsRepository;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    private final UserSkillsRepository userSkillsRepository;

    public UserSkillServiceImpl(UserSkillsRepository userSkillsRepository) {

        this.userSkillsRepository = userSkillsRepository;
    }

    @Override
    public Optional<UserSkill> getById(Integer id) {
        return userSkillsRepository.findById(id);
    }

    @Override
    public UserSkill save(UserSkill userSkill) {
        return userSkillsRepository.save(userSkill);
    }
}
