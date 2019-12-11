package com.ons.group2.ons_client_project.model.dto.skill;

import com.ons.group2.ons_client_project.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This classed is used to expose information of a skill to the frontend.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDetail {

    private Integer id;

    private String title;

    private String description;

    private Short confidence;

    private Category category;

    private Integer is_public;
}
