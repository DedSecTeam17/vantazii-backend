package com.example.vantazii.CustomerRole;


import com.example.vantazii.CustomerRole.CompositeAtrr.CustomerRoleID;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.role.AppRole;
import com.example.vantazii.role.AppRoleRepo;
import com.example.vantazii.role.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerRoleService {


    AppRoleRepo appRoleRepo;
    CustomerRoleRepo customerRoleRepo;


    public  void createRoleFor(Customer customer,RoleName roleName){
        Optional<AppRole> adminRole = appRoleRepo.findAppRoleByRoleName(roleName);
        if (adminRole.isPresent()){
            CustomerRole customerRole = new CustomerRole();
            customerRole.setAppRole(adminRole.get());
            customerRole.setCustomer(customer);
            CustomerRoleID customerRoleID = new CustomerRoleID();
            customerRoleID.setAppRoleID(adminRole.get().getId());
            customerRoleID.setCustomerId(customer.getId());
            customerRole.setCustomerRoleID(customerRoleID);
            customerRoleRepo.save(customerRole);
        }
    }


}
