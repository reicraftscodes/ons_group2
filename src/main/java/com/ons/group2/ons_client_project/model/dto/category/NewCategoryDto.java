package com.ons.group2.ons_client_project.model.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCategoryDto {

    @NotBlank
    private String name;

    private Integer parentCategoryId;
}
