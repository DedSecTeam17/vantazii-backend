package com.example.vantazii.team;

import com.example.vantazii.league.League;
import com.example.vantazii.match.Match;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "Team")
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(
            name = "team_name",
            nullable = false
    )
    @NotBlank
    @NotNull
    private String teamName;

    @Column(
            name = "team_logo",
            nullable = false
    )
    @NotBlank
    @NotNull
    private String teamLogo;

    @Column(
            name = "team_description",
            nullable = false
    )
    @NotBlank
    @NotNull
    private String teamDescription;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "league_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "league_fk"
            )
    )
    private League league;

    @OneToMany(
            mappedBy = "homeTeam"

    )
    @JsonIgnore
    private List<Match> homeMatches;


    @OneToMany(
            mappedBy = "awayTeam"
    )
    @JsonIgnore
    private List<Match> awayMatches;
}
