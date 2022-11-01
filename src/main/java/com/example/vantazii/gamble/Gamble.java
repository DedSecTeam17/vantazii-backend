package com.example.vantazii.gamble;


import com.example.vantazii.customer.Customer;
import com.example.vantazii.match.Match;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "Gamble")
@Table(name = "gamble")
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

    @ManyToOne(
    )
    @JoinColumn(
            name = "match_gamble_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "match_gamble_fk"
            )
    )
    private Match match;


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
