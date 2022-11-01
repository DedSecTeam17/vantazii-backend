package com.example.vantazii.match;

import com.example.vantazii.league.League;
import com.example.vantazii.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepo extends CrudRepository<Match, UUID> {
    List<Match> findAllByLeagune(League league);
    List<Match> findAllByAwayTeam(Team awayTeam);
    List<Match> findAllByHomeTeam(Team homeTeam);


}
