package com.ons.group2.ons_client_project.model.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SafeUserDetails {

    private String fullName;

    private String email;

    private String profileUrl;
}
