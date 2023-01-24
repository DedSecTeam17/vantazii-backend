
package com.example.vantazii.footBallService.DTO.FixtureResponse;


import lombok.Data;

@Data
public class Fixture {

    private Integer id;
    private String referee;
    private String timezone;
    private String date;
    private Integer timestamp;
    private Periods periods;
    private Venue venue;
    private Status status;


}
