package com.example.vantazii.customer;


import com.example.vantazii.CustomerRole.CustomerRole;
import com.example.vantazii.core.CustomAnnotation.phoneNumber.PhoneNumber;
import com.example.vantazii.gamble.Gamble;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
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
//        @UniqueConstraint(
//                name = "customer_unique_email_constraint",
//                columnNames = "email"
//        ),
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

    @Column(
            name = "user_name",
            columnDefinition = "TEXT"
    )
    @Nullable
    private String userName;


    @Column(
            name = "email",
            columnDefinition = "TEXT"

    )
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




    @Column(
            name = "profile_image_url",
            columnDefinition = "TEXT"
    )
    @Nullable
    private String profileImageUrl;


    //
    @Column(
            name = "created_at",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    @JsonIgnore
    private LocalDateTime createdAt;



    @Column(
            name = "verified",
            columnDefinition = "BOOL"

    )
    private boolean verified;



    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "customer",
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private List<CustomerRole> customerRoleIDS = new ArrayList<>();


    @OneToMany(
            mappedBy = "customer"
    )
    @JsonIgnore
    private List<Gamble> gambles;

}
