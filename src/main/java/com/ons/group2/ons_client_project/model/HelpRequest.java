package com.ons.group2.ons_client_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "help_request")
public class HelpRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "help_request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user_id;

    @Column(name = "date_posted")
    private Date date_posted;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    @Length(min = 30,message = "Please explain your offer a little more to ensure you're able to help the right people.")
    private String description;

    @Column(name = "method_of_contact")
    private String method_of_contact;

}
