package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.adaptors.UserSkillAdaptor;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;
import com.ons.group2.ons_client_project.repository.UserSkillsRepository;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<UserSkill> getAllForUser(Integer userId) {
        return userSkillsRepository.findAllByUser_Id(userId);
    }

    @Override
    public UserSkill save(NewSkillDto newSkillDto) {
        var skill = UserSkillAdaptor.createUserSkill(newSkillDto);

        return userSkillsRepository.save(skill);
    }

    @Override
    public void removeSkill(User user, Integer skillId) {
        userSkillsRepository.deleteByIdAndUser_Id(skillId, user.getId());
    }
}
