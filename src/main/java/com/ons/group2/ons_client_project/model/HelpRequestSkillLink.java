package com.ons.group2.ons_client_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "help_request_skill_link_table")
public class HelpRequestSkillLink {
    @Id
    @Column(name = "help_request_skill_link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long help_request_skill_link_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private UserSkill skill_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "help_request_id")
    @NotNull
    private HelpRequest help_request_id;

}
