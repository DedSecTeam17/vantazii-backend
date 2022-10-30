package com.example.vantazii.customer;


import com.example.vantazii.CustomerRole.CustomerRole;
import com.example.vantazii.core.CustomAnnotation.phoneNumber.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "Customer")
@RequiredArgsConstructor
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(
                name = "customer_unique_email_constraint",
                columnNames = "email"
        ),
        @UniqueConstraint(
                name = "customer_unique_phone",
                columnNames = "phone_number"
        )
})
public class Customer {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private UUID id;
    //
    @NotNull
    @NotBlank(message = "Name is required :D")
    @Column(
            name = "user_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String userName;


    //
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull
    @NotBlank
    @Email
    private String email;


    @Column(
            name = "phone_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull
    @NotBlank
    @PhoneNumber
    private String phoneNumber;


    //
    @Column(
            name = "created_at",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    @JsonIgnore
    private LocalDateTime createdAt;


    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "customer",
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private List<CustomerRole> customerRoleIDS = new ArrayList<>();


}
