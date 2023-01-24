
package com.example.vantazii.footBallService.DTO.FixtureResponse;

import lombok.Data;

@Data
public class Score {

    private TeamsResult halftime;
    private TeamsResult fulltime;
    private TeamsResult extratime;
    private TeamsResult penalty;


}
