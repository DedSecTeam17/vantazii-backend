package com.example.vantazii.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, UUID> {

    Customer findCustomerByUserName(String userName);

    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);

}
