package com.ons.group2.ons_client_project.model.dto.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSkillDto {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    @Size(max = 5)
    private Short confidence;
}
