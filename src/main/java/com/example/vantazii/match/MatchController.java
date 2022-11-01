package com.example.vantazii.match;


import com.example.vantazii.match.dto.CreateMatchDto;
import com.example.vantazii.match.dto.UpdateMatchDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/match")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Match>> findAll(@RequestParam(required = false) String leagueId, @RequestParam(required = false) String homeTeam, @RequestParam(required = false) String awayTeam) {
        if (leagueId != null) {
            return ResponseEntity.ok(matchService.allMatchesForLeague(leagueId));
        } else if (homeTeam != null) {
            return ResponseEntity.ok(matchService.allMatchesByHomeTeam(homeTeam));
        } else if (awayTeam != null) {
            return ResponseEntity.ok(matchService.allMatchesByAwayTeam(awayTeam));
        } else {
            return ResponseEntity.ok(matchService.findAll());
        }
    }

    @GetMapping(path = "/find/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Match> find(@PathVariable String id) {
        return ResponseEntity.ok(matchService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Match> save(@RequestBody CreateMatchDto createMatchDto) {
        return ResponseEntity.ok(matchService.saveMatch(createMatchDto));
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<Match> update(@RequestBody UpdateMatchDto updateMatchDto, @PathVariable String id) {
        return ResponseEntity.ok(matchService.updateMatch(updateMatchDto, id));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Match> delete(@PathVariable String id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

}
