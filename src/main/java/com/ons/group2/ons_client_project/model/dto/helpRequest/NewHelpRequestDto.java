package com.ons.group2.ons_client_project.model.dto.helpRequest;

import com.ons.group2.ons_client_project.model.UserSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewHelpRequestDto {

    @NotNull(message = "Please enter a title")
    private String title;

    @NotNull(message = "You must tag at least one skill for others to be able to find your offer!")
    private List<UserSkill> taggedSkills;

    private String methodOfContact;

    @NotNull(message = "Let us know a little more about your request.")
    @Length(min = 30,message = "Please explain your request a little more to ensure you're able to  get the right help.")
    private String description;


}
