package com.example.vantazii.CustomerRole;


import com.example.vantazii.CustomerRole.CompositeAtrr.CustomerRoleID;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.role.AppRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@RequiredArgsConstructor
@Data
@Entity(name = "CustomerRole")
@Table(name = "customer_role")
public class CustomerRole {

    @Id
    private CustomerRoleID customerRoleID;



    @MapsId("appRoleID")
    @ManyToOne
    @JoinColumn(
            name = "app_role_id",
            foreignKey = @ForeignKey(
                    name = "customer_app_role_frk"
            )
    )
    private AppRole appRole;


    @MapsId("customerId")
    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(
                    name = "customer_id_frk"
            )
    )
    private Customer customer;


}
