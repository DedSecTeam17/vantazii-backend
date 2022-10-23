package com.example.vantazii.core.security.auth;

import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.core.exception.PhonenumberNotFoundException;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.customer.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final CustomerRepo customerRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws AuthenticationException {
        Customer customer = this.customerByPhone(phoneNumber);
        return new User(customer.getEmail(), "",
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    public Customer customerByPhone(String phone) {
        Optional<Customer> user = customerRepo.findCustomerByPhoneNumber(phone);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new PhonenumberNotFoundException("customer with this phone not found");
        }
    }

    public Customer saveUser(Customer userDTO) {
        try{
            Customer customer = new Customer();
            customer.setCreatedAt(LocalDateTime.now());
            customer.setEmail(userDTO.getEmail());
            customer.setUserName(userDTO.getUserName());
            customer.setPhoneNumber(userDTO.getPhoneNumber());
            return customerRepo.save(customer);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage(), ApiExceptionType.DEFAULT);
        }
    }


    public boolean customerExist(String phoneNumber) {
        Optional<Customer> customer = customerRepo.findCustomerByPhoneNumber(phoneNumber);
        return customer.isPresent();
    }
}
