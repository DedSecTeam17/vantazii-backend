package com.example.vantazii.match;

import com.example.vantazii.gamble.Gamble;
import com.example.vantazii.league.League;
import com.example.vantazii.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "match")
@Table(name = "Match")
@Data
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(
            name = "start_date",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDate;


    @Column(
            name = "end_date",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDate;


    @Column(
            name = "result",
            nullable = false
    )
    @NotBlank
    @NotNull
    private String result;


    @ManyToOne(
    )
    @JoinColumn(
            name = "home_team_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "home_team_id_fk"
            )
    )
    private Team homeTeam;


    @ManyToOne()
    @JoinColumn(
            name = "away_team_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "away_team_id_fk"
            )
    )

    private Team awayTeam;


    @ManyToOne()
    @JoinColumn(
            name = "league_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "league_matches_fk"
            )
    )
    private League leagune;

}
