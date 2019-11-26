package com.ons.group2.ons_client_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "help_offer_skill_link_table")
public class HelpOfferSkillLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @NotNull
    private Long skill_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "help_offer_id")
    @NotNull
    private HelpOffer help_offer_id;
}
