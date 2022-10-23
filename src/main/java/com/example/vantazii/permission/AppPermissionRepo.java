package com.example.vantazii.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppPermissionRepo extends JpaRepository<AppPermission, UUID> {
}
