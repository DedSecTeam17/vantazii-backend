package com.example.vantazii.footBallService;


import com.example.vantazii.footBallService.DTO.FixtureResponse.FixtureResponse;
import com.example.vantazii.footBallService.DTO.FootBallDefaultResponse;
import com.example.vantazii.footBallService.DTO.LeagueResponse.LeagueResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/footbal-service/")
@AllArgsConstructor
public class OpenFootballController {


    private OpenFootballClient openFootballClient;


    @GetMapping("englad-leagues")
    public ResponseEntity<FootBallDefaultResponse<LeagueResponse>> findAllLeaguesForEngland() {
        FootBallDefaultResponse<LeagueResponse> leaguesResponse = openFootballClient.findAllLeaguesByCountry("England");
        return ResponseEntity.ok(leaguesResponse);
    }


    @GetMapping("leagues-fixtures/{leagueId}")
    public ResponseEntity<FootBallDefaultResponse<FixtureResponse>> findAllLeaguesFixtures(@PathVariable(value = "leagueId") int leagueId) {
        FootBallDefaultResponse<FixtureResponse> response = openFootballClient.fixturesForLeague(leagueId, 10);
        return ResponseEntity.ok(response);
    }

    @GetMapping("fixtures/{fixtureId}")
    public ResponseEntity<FootBallDefaultResponse<FixtureResponse>> findFixtureById(@PathVariable(value = "fixtureId") String fixtureId) {
        FootBallDefaultResponse<FixtureResponse> response = openFootballClient.findFixtureById(fixtureId);
        return ResponseEntity.ok(response);
    }

    private static String getCurrentDateAsYMD() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(localDateTime.format(dateTimeFormatter));
        return localDateTime.format(dateTimeFormatter);
    }


//    all leagesas


//    all fixtures


}
