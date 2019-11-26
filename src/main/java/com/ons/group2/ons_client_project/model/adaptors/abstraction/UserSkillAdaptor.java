package com.ons.group2.ons_client_project.model.adaptors.abstraction;

import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;

public interface UserSkillAdaptor {
    UserSkill createUserSkill(NewSkillDto newSkillDto);
}
