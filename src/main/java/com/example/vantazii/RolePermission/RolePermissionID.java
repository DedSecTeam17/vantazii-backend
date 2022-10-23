package com.example.vantazii.RolePermission;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class RolePermissionID implements Serializable {

    @Column(
            name = "permission_role_id",
            nullable = false
    )
    private UUID roleID;

    @Column(
            name = "permission_id",
            nullable = false
    )
    private UUID permissionID;

}
