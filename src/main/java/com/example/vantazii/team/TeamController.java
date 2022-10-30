package com.example.vantazii.team;

import com.example.vantazii.team.dto.CreateTeamDto;
import com.example.vantazii.team.dto.UpdateTeamDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/team")
@AllArgsConstructor
public class TeamController {


    TeamService teamService;

    @GetMapping(path = "/{leagueId}/all")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Team>> all(@PathVariable String leagueId){
        return ResponseEntity.ok(teamService.all(leagueId));
    }


    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Team> findOne(@PathVariable String id){
        return ResponseEntity.ok(teamService.findOne(id));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Team> save(@Valid @ModelAttribute CreateTeamDto createTeamDto){
        return  ResponseEntity.ok(teamService.createTeam(createTeamDto));
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Team> update(@PathVariable String id , @Valid @ModelAttribute UpdateTeamDto updateTeamDto){
        return  ResponseEntity.ok(teamService.updateTeam(updateTeamDto,id));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable String id ){
        teamService.deleteTeam(id);
        return  ResponseEntity.noContent().build();
    }


}
