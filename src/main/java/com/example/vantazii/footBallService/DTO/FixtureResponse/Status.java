
package com.example.vantazii.footBallService.DTO.FixtureResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Status {

    @JsonProperty("long")
    private String _long;

    @JsonProperty("short")
    private String _short;
    private int elapsed;


}
