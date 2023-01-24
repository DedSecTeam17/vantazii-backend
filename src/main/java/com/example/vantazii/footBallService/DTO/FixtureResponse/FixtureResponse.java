
package com.example.vantazii.footBallService.DTO.FixtureResponse;

import lombok.Data;



@Data
public class FixtureResponse {
    private Fixture fixture;
    private League league;
    private Teams teams;
    private TeamsResult goals;
    private Score score;
}
