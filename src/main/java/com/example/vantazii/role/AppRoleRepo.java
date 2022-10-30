package com.example.vantazii.role;

import com.example.vantazii.role.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppRoleRepo extends JpaRepository<AppRole, UUID> {

    Optional<AppRole> findAppRoleByRoleName(RoleName roleName);

}
