package com.ons.group2.ons_client_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "help_offer_skill_link_table")
public class HelpOfferSkillLink {
    @Id
    @Column(name = "help_offer_skill_link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long help_offer_skill_link_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private UserSkill skill_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "help_offer_id")
    @NotNull
    private HelpOffer help_offer_id;
}
