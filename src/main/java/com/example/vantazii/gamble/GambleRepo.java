package com.example.vantazii.gamble;

import com.example.vantazii.customer.Customer;
import com.example.vantazii.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface GambleRepo extends JpaRepository<Gamble, UUID> {

    List<Gamble> findAllByCustomer(Customer customer);
//    List<Gamble> findAllByMatch(Match match);
}
