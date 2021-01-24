package com.accounts.login.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "skill_name")
    private String skillName;

    @JoinColumn(name = "link")
    private String link;

    @JoinColumn(name = "custom")
    private String custom;

    @JoinColumn(name = "ranking")
    private Integer ranking;

    @JoinColumn(name = "rating")
    private Integer rating;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
