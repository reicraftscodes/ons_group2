package com.ons.group2.ons_client_project.controllers.rest;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;
import com.ons.group2.ons_client_project.service.UserSkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/Skills")
@Slf4j
public class UserSkillRestController {

    private final UserSkillService userSkillService;

    public UserSkillRestController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @PostMapping("/AddSkill")
    public ResponseEntity addSkill(@RequestBody @Valid NewSkillDto newSkillDto,
            @SessionAttribute("user") User user) {

        log.info(newSkillDto.toString());

        newSkillDto.setUser(user);
        userSkillService.save(newSkillDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/RemoveSkill/{skillId}")
    @Transactional
    public ResponseEntity addSkill(@PathVariable("skillId") Integer skillId,
                                   @SessionAttribute("user") User user) {

        userSkillService.removeSkill(user, skillId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity getAllForUser(@SessionAttribute("user") User user) {
        return ResponseEntity.ok(userSkillService.getAllForUser(user.getId()));
    }
}
