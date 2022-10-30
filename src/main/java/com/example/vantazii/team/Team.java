package com.example.vantazii.team;

import com.example.vantazii.league.League;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
}
