package com.example.vantazii.permission;

import com.example.vantazii.RolePermission.RolePermission;
import com.example.vantazii.permission.enums.PermissionName;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity(name = "AppPermission")
@Table(name = "app_permission", uniqueConstraints = {
        @UniqueConstraint(name = "permission_name_unique_constraint", columnNames = "permission_name")
})
@Data
@RequiredArgsConstructor
public class AppPermission {
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
            name = "permission_name"
    )
    private PermissionName permissionName;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "appPermission",
            fetch = FetchType.EAGER
    )
    private List<RolePermission> rolePermissions = new ArrayList<>();

}
// 1 role has N P -- role_id permssion_id