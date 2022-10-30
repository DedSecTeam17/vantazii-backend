package com.example.vantazii.team;

import com.example.vantazii.league.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TeamRepo extends JpaRepository<Team, UUID> {

    List<Team> findAllByLeague(League league);
}
