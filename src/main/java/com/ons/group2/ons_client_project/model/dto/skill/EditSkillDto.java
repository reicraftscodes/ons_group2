package com.ons.group2.ons_client_project.model.dto.skill;

import com.ons.group2.ons_client_project.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditSkillDto {
    @NotNull
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Min(1)
    @Max(5)
    private Short confidence;

    private Integer categoryId;

    private Integer is_public;

}
