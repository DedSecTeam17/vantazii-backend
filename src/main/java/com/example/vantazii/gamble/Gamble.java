package com.example.vantazii.gamble;


import com.example.vantazii.customer.Customer;
import com.example.vantazii.match.Match;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "Gamble")
@Table(name = "gamble",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fixture_id","customer_gamble_id"})
})
@Data
public class Gamble {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(
            name = "is_winner",
            nullable = true
    )
    private boolean isWinner;


    @Column(
            name = "expected_result",
            nullable = false
    )
    @NotBlank
    @NotNull
    private String expectedResult;


    @NotNull
    @NotBlank
    @Column(
            name = "fixture_id",
            nullable = false
    )
    private String fixtureId;


    @ManyToOne(
    )
    @JoinColumn(
            name = "customer_gamble_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "customer_gamble_fk"
            )
    )
    private Customer customer;

}
