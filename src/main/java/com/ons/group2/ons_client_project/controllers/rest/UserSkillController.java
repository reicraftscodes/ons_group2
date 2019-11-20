package com.ons.group2.ons_client_project.controllers.rest;

import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSkillController {

    private final UserSkillService userSkillService;

    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }
}
