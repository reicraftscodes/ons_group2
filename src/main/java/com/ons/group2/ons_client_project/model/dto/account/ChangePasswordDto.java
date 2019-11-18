package com.ons.group2.ons_client_project.model.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
    @NotNull
    public String oldPassword;

    @NotNull(message="Password is compulsory")
    @Length(min=5, message="Password should be at least 5 characters")
    public String newPassword;

    @NotNull
    public String newPasswordCf;
}
