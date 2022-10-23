package com.example.vantazii.core.security.auth.response;


import com.example.vantazii.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private Customer customer;
}
