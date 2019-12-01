package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.adaptors.abstraction.UserSkillAdaptor;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;
import com.ons.group2.ons_client_project.repository.UserSkillsRepository;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    private final UserSkillsRepository userSkillsRepository;
    private final UserSkillAdaptor userSkillAdaptor;

    public UserSkillServiceImpl(UserSkillsRepository userSkillsRepository, UserSkillAdaptor userSkillAdaptor) {

        this.userSkillsRepository = userSkillsRepository;
        this.userSkillAdaptor = userSkillAdaptor;
    }

    @Override
    public Optional<UserSkill> getById(Integer id) {
        return userSkillsRepository.findById(id);
    }

    @Override
    public UserSkill save(NewSkillDto newSkillDto) {
        var skill = userSkillAdaptor.createUserSkill(newSkillDto);

        return userSkillsRepository.save(skill);

    }

    @Override
    public List<UserSkill> getAllByCategoryId(Integer categoryId) {
        return userSkillsRepository.findAllByCategory_Id(categoryId);
    }

    @Override
    public UserSkill create(NewSkillDto newSkillDto) {
        var skill = userSkillAdaptor.createUserSkill(newSkillDto);

        return userSkillsRepository.save(skill);
    }

    @Override
    public UserSkill save(UserSkill userSkill) {
        return userSkillsRepository.save(userSkill);
    }

    @Override
    public void removeSkill(User user, Integer skillId) {
        userSkillsRepository.deleteByIdAndUser_Id(skillId, user.getId());
    }

    @Override
    public List<UserSkill> getAllForUser(Long userId) {
        return userSkillsRepository.findAllByUser_Id(userId);
    }
}
