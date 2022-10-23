package com.example.vantazii.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, UUID> {

    Customer findCustomerByUserName(String userName);

    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);

}
