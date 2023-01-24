
package com.example.vantazii.footBallService.DTO;

import com.example.vantazii.footBallService.DTO.LeagueResponse.Paging;
import com.example.vantazii.footBallService.DTO.LeagueResponse.Parameters;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class FootBallDefaultResponse<T> {
    private String get;
    private Parameters parameters;
//    private List errors;
    private Integer results;
    private Paging paging;
    private List<T> response;
}
