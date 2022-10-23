package com.example.vantazii.RolePermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface RolePermissionRepo extends JpaRepository<RolePermission, UUID> {
}
