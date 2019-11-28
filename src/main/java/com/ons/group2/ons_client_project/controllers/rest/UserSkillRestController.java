package com.ons.group2.ons_client_project.controllers.rest;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.dto.skill.NewSkillDto;
import com.ons.group2.ons_client_project.model.dto.skill.SkillDetail;
import com.ons.group2.ons_client_project.security.UserPrincipal;
import com.ons.group2.ons_client_project.service.UserSkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;

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
                                   Authentication auth) {
        var principal = (UserPrincipal) auth.getPrincipal();
        var user = principal.getUser();

        newSkillDto.setUser(user);
        userSkillService.create(newSkillDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/RemoveSkill/{skillId}")
    @Transactional
    public ResponseEntity addSkill(@PathVariable("skillId") Integer skillId,
                                   Authentication auth) {
        var principal = (UserPrincipal) auth.getPrincipal();
        var user = principal.getUser();

        var skillOpt = userSkillService.getById(skillId);
        if(skillOpt.isEmpty()) return ResponseEntity.notFound().build();
        if(!skillOpt.get().getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).body("Skill doesn't belong to user.");
        }

        userSkillService.removeSkill(user, skillId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/User")
    public ResponseEntity getAllForUser(Authentication auth) {

        var principal = (UserPrincipal) auth.getPrincipal();
        var user = principal.getUser();

        var userSkills = userSkillService.getAllForUser(user.getId());
        var skillDetails = new ArrayList<SkillDetail>();

        for (var skill : userSkills) {
            skillDetails.add(new SkillDetail(
                    skill.getId(),
                    skill.getTitle(),
                    skill.getDescription(),
                    skill.getConfidence(),
                    skill.getCategory()
            ));
        }

        return ResponseEntity.ok(skillDetails);
    }
}
