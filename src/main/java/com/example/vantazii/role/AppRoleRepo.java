package com.example.vantazii.role;
import com.example.vantazii.customer.CustomerRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AppRoleRepo extends JpaRepository<AppRole, UUID> {
}
