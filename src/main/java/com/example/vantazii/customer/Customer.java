package com.example.vantazii.customer;


import com.example.vantazii.core.AppRegxHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,updatable = false,insertable = false)
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
    @Pattern(regexp = "^\\\\d{10}$")
    private String phoneNumber;


    @Column(
            name = "sms_code",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull
    @NotBlank
    @Max(4)
    @Min(4)
    private String smsCode;


    @Column(
            name = "verified",
            nullable = false,
            columnDefinition = "BOOLEAN DEFAULT false"
    )
    @NotNull
    @NotBlank
    private boolean verified;

//
    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;


}
