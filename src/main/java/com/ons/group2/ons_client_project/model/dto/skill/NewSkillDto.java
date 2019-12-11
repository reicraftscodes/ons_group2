package com.ons.group2.ons_client_project.model.dto.skill;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSkillDto {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer confidence;

    private Integer categoryId;

    private User user;

    private Integer is_public;

    public Integer getIsPubic(){
        return is_public;
    }
}
