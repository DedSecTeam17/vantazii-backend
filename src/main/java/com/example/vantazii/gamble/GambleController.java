package com.example.vantazii.gamble;

import com.example.vantazii.gamble.dto.CreateGambleDto;
import com.example.vantazii.gamble.dto.UpdateGambleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/gamble")
@AllArgsConstructor
public class GambleController {
    private GambleService gambleService;


    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Gamble>> findAll(@RequestParam(required = false) boolean byCustomer, @RequestParam(required = false) String matchId) {
        if (byCustomer) {
            return ResponseEntity.ok(gambleService.findAllByCustomer());
        } else if (matchId != null) {
            return ResponseEntity.ok(gambleService.findAllByMatch(matchId));
        } else {
            return ResponseEntity.ok(gambleService.all());
        }
    }


    @PreAuthorize("hasAuthority('READ')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Gamble> findAll(@PathVariable String id) {
        return ResponseEntity.ok(gambleService.find(id));
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")

    public ResponseEntity<Gamble> save(@Valid @RequestBody CreateGambleDto createGambleDto) {
        return ResponseEntity.ok(gambleService.saveGmable(createGambleDto));
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    public ResponseEntity<Gamble> update(@Valid @RequestBody UpdateGambleDto updateGambleDto, @PathVariable String id) {
        return ResponseEntity.ok(gambleService.updateGmable(updateGambleDto, id));
    }


    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Gamble> delete(@Valid @PathVariable String id) {
        gambleService.deleteGamble(id);
        return ResponseEntity.noContent().build();
    }
}
