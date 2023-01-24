
package com.example.vantazii.footBallService.DTO.LeagueResponse;

import lombok.Data;

@Data
public class Season {


    private Integer year;
    private String start;
    private String end;
    private Boolean current;
    private Coverage coverage;

}
