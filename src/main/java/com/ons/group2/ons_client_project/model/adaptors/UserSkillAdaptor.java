package com.ons.group2.ons_client_project.model.adaptors;

import com.ons.group2.ons_client_project.model.UserSkill;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;
import com.ons.group2.ons_client_project.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class UserSkillAdaptor implements com.ons.group2.ons_client_project.model.adaptors.abstraction.UserSkillAdaptor {

    private final CategoryService categoryService;

    public UserSkillAdaptor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public UserSkill createUserSkill(NewSkillDto newSkillDto) {

        var category = categoryService.getById(newSkillDto.getCategoryId());

        return new UserSkill(
                null,
                newSkillDto.getTitle(),
                newSkillDto.getDescription(),
                newSkillDto.getConfidence().shortValue(),
                newSkillDto.getUser(),
                category.get()
        );
    }
}
