
package com.example.vantazii.footBallService.DTO.LeagueResponse;

import java.util.List;

import lombok.Data;

@Data
public class LeagueResponse {

    private League league;
    private Country country;
    private List<Season> seasons;

}
