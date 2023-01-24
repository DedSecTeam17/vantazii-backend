
package com.example.vantazii.footBallService.DTO.LeagueResponse;


import lombok.Data;

@Data
public class Coverage {
    private Fixtures fixtures;
    private Boolean standings;
    private Boolean players;
    private Boolean topScorers;
    private Boolean topAssists;
    private Boolean topCards;
    private Boolean injuries;
    private Boolean predictions;
    private Boolean odds;

}
