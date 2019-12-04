package com.ons.group2.ons_client_project.model.dto.help_offer;

import com.ons.group2.ons_client_project.model.UserSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewHelpOfferDto {
    @NotNull(message = "Please enter a title")
    @Length(min = 1,message = "Please enter a title")
    private String title;

    @NotEmpty(message = "You must tag at least one skill for others to be able to find your offer!")
    private List<UserSkill> taggedSkills;

    private String methodOfContact;

    @NotNull(message = "Let us know a little more about your offer.")
    @Length(min = 30,message = "Please explain your offer a little more to ensure you're able to help the right people.")
    private String description;
}
