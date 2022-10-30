package com.example.vantazii.team;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeamRepo extends CrudRepository<Team, UUID> {
}
