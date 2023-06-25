package com.example.vantazii.chat;


import com.example.vantazii.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Chat")
@Table(name = "chat")
@Data
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @NotNull
    @NotBlank
    @Column(
            name = "message",
            nullable = false
    )
    private String message;


    @ManyToOne(
    )
    @JoinColumn(
            name = "customer_sender_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "customer_sender_fk"
            )
    )
    private Customer sender;




    @ManyToOne(
    )
    @JoinColumn(
            name = "customer_reciver_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "customer_reciver_fk"
            )
    )
    private Customer reciver;


    @Column(
            name = "created_at",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;


}
