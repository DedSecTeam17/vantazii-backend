package com.example.vantazii.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, UUID> {

    Optional<Customer> findCustomerByUserName(String userName);


    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);


    Optional<Customer> findCustomerById(UUID id);

//    Optional<Customer> findCustomerByEmail(String email);

}
