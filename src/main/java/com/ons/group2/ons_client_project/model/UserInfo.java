package com.ons.group2.ons_client_project.model;

import lombok.Data;

@Data
//@AllArgsConstructor
public class UserInfo {

    private String first_name;
    private String skills;

    public UserInfo(String first_name) {
        this.first_name = first_name;
    }

    public UserInfo(String first_name, String skills) {
        this.first_name = first_name;
        this.skills = skills;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
