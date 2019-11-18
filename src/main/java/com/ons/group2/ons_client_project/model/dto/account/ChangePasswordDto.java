package com.ons.group2.ons_client_project.model.dto.account;

import com.ons.group2.ons_client_project.model.User;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordDto {
    @Valid
    public User user;

    @NotNull
    public String newPassword;
}
