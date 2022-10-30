package com.example.vantazii.core.security.auth;

import com.example.vantazii.CustomerRole.CustomerRole;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.core.exception.PhonenumberNotFoundException;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.customer.CustomerRepo;
import com.example.vantazii.role.AppRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final CustomerRepo customerRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws AuthenticationException {
        Customer customer = this.customerByPhone(phoneNumber);
        return new User(customer.getEmail(), "",this.getPermissionsFromCustomer(customer));
    }

    private List<SimpleGrantedAuthority> getPermissionsFromCustomer(Customer customer){
        List<SimpleGrantedAuthority> result = new ArrayList<>();
     List<CustomerRole> roles =    customer.getCustomerRoleIDS();

     roles.forEach(role ->{
         AppRole appRole = role.getAppRole();
         List<SimpleGrantedAuthority> permissions = appRole.getRolePermissions().stream().map(rolePermission -> new SimpleGrantedAuthority(rolePermission.getAppPermission().getPermissionName().name())).collect(Collectors.toList());
         permissions.add(new SimpleGrantedAuthority("ROLE_"+appRole.getRoleName().name()));
         result.addAll(permissions);
     });

     return result;

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
