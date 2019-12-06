package com.ons.group2.ons_client_project.model.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInfoDto {

    private Long userId;

    private String firstName;
    private String lastName;

    @Email
    private String email;
}
