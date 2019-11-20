package com.ons.group2.ons_client_project.model.adaptors;

import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;

public class UserSkillAdaptor {

    public static UserSkill createUserSkill(NewSkillDto newSkillDto) {
        return new UserSkill(
                null,
                newSkillDto.getTitle(),
                newSkillDto.getDescription(),
                newSkillDto.getConfidence()
        );
    }
}
