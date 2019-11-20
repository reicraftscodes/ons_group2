package com.ons.group2.ons_client_project.controllers.rest;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(name = "/api/UserSkill")
public class UserSkillController {

    private final UserSkillService userSkillService;

    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @PostMapping(name = "/AddSkill")
    public ResponseEntity addSkill(
            @RequestParam("newSkill") @Valid NewSkillDto newSkillDto,
            @SessionAttribute("user") User user
    ) {

        userSkillService.save(newSkillDto);

        return ResponseEntity.ok().build();
    }
}
