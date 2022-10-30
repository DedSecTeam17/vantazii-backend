package com.example.vantazii.league;


import com.example.vantazii.core.shared.DefaultResponse;
import com.example.vantazii.league.dto.LeagueDto;
import com.example.vantazii.league.dto.UpdateLeagueDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/league")
@AllArgsConstructor
public class LeagueController {

    private LeagueService leagueService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('READ')")
    public List<League> getAllLeagues() {
        return leagueService.allLeagues();
    }

    @GetMapping("/{id}")

    public ResponseEntity<League> findOne(@PathVariable String id) {
        return ResponseEntity.ok(leagueService.findById(UUID.fromString(id)));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@Valid @ModelAttribute LeagueDto leagueDto) {
        return ResponseEntity.ok(leagueService.saveLeague(leagueDto));
    }


    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable String id) {
        leagueService.deleteLeague(UUID.fromString(id));
        return ResponseEntity.ok(new DefaultResponse("Deleted sucessfully", HttpStatus.OK));
    }


    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @ModelAttribute UpdateLeagueDto leagueDto) {
        League updateLeague = leagueService.updateLeague(leagueDto, UUID.fromString(id));
        return ResponseEntity.ok(updateLeague);
    }

}
