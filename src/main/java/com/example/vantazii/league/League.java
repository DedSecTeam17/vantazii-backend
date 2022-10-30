package com.example.vantazii.league;


import com.example.vantazii.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "League")
@Table(name = "league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(
            name = "league_name",
            nullable = false
    )
    @NotBlank
    @NotNull
    private String leagueName;


    @Column(
            name = "league_logo",
            nullable = false
    )
    @NotBlank
    @NotNull
    private String leagueLogo;

    @Column(
            name = "league_description",
            nullable = false
    )
    @NotBlank
    @NotNull
    private String leagueDescription;

    @OneToMany(
            mappedBy = "league",
            fetch = FetchType.EAGER
    )
            @JsonIgnore
    List<Team> teams;
}
