package com.example.vantazii.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface LeagueRepo extends JpaRepository<League, UUID> {
}
