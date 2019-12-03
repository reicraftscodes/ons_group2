package com.ons.group2.ons_client_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private String profileUrl;


    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
