package com.example.vantazii.RolePermission;


import com.example.vantazii.permission.AppPermission;
import com.example.vantazii.role.AppRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity(name = "RolePermission")
@Table(name = "app_role_permission")
public class RolePermission {
        @Id
        private RolePermissionID rolePermissionID;


        @MapsId("permissionID")
        @ManyToOne
        @JoinColumn(
                name = "app_permission_id",
                foreignKey = @ForeignKey(
                        name = "app_permission_id_fk"
                )
        )
        private AppPermission appPermission;


        @MapsId("roleID")
        @ManyToOne
        @JoinColumn(
                name = "app_permission_role_id",
                foreignKey = @ForeignKey(
                        name = "app_role_id_fk"
                )
        )
        private AppRole appRolePermission;


}
