package com.example.vantazii.CustomerRole.CompositeAtrr;


import com.example.vantazii.role.AppRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@RequiredArgsConstructor
public class CustomerRoleID implements Serializable {
    @Column(
            name = "customer_id",
            nullable = false
    )
    private UUID customerId;


    @Column(
            name = "app_role_id",
            nullable = false
    )
    private UUID appRoleID;



}
