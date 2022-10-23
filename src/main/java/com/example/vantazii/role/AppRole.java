package com.example.vantazii.role;


import com.example.vantazii.CustomerRole.CustomerRole;
import com.example.vantazii.RolePermission.RolePermission;
import com.example.vantazii.role.enums.RoleName;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Entity(name = "Role")
@Table(name = "app_role",uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_role",
                columnNames = "role_name"
        )
})
public class AppRole {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "role_name"
    )
    private RoleName roleName;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "appRole"

    )
    private List<CustomerRole> customerRoleIDS = new ArrayList<>();


    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "appRolePermission",
            fetch = FetchType.EAGER

    )
    private List<RolePermission> rolePermissions = new ArrayList<>();

}
