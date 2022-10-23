package com.example.vantazii;

import com.example.vantazii.CustomerRole.CompositeAtrr.CustomerRoleID;
import com.example.vantazii.CustomerRole.CustomerRole;
import com.example.vantazii.CustomerRole.CustomerRoleRepo;
import com.example.vantazii.RolePermission.RolePermission;
import com.example.vantazii.RolePermission.RolePermissionID;
import com.example.vantazii.RolePermission.RolePermissionRepo;
import com.example.vantazii.core.config.TwillioConfig;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.customer.CustomerRepo;
import com.example.vantazii.permission.AppPermission;
import com.example.vantazii.permission.AppPermissionRepo;
import com.example.vantazii.permission.enums.PermissionName;
import com.example.vantazii.role.AppRole;
import com.example.vantazii.role.AppRoleRepo;
import com.example.vantazii.role.enums.RoleName;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class VantaziiApplication {


    @Autowired
    private TwillioConfig twilioConfig;
    public static void main(String[] args) {
        SpringApplication.run(VantaziiApplication.class, args);
    }



    @PostConstruct
    public void initTwilio(){
        System.out.println("initTwilio-------------> {}{}{}");
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepo customerRepo, CustomerRoleRepo customerRoleRepo, AppRoleRepo appRoleRepo, AppPermissionRepo appPermissionRepo, RolePermissionRepo rolePermissionRepo){
        return args -> {

            Customer customer = new Customer();
//
//            customer.setCreatedAt(LocalDateTime.now());
//            customer.setEmail("melamin100@yahoo.com");
//            customer.setSmsCode("2314");
//            customer.setVerified(true);
//            customer.setUserName("mohammed elamin");
//            customer.setPhoneNumber("0525221632");
//            customer.setPassword("mohamed1337");
//
////
//            customerRepo.save(customer);
//            AppRole appRole = new AppRole();
//            appRole.setRoleName(RoleName.ADMIN);
//
//            appRoleRepo.save(appRole);
//            AppPermission appPermission =new AppPermission();
//            appPermission.setPermissionName(PermissionName.READ);
//            appPermissionRepo.save(appPermission);
//            List<Customer> customers  = customerRepo.findAll();
//            List<AppRole> roles  = appRoleRepo.findAll();
//            List<AppPermission> permissions  = appPermissionRepo.findAll();

//            RolePermission rolePermission = new RolePermission();
//            RolePermissionID rolePermissionID = new RolePermissionID();
//            rolePermissionID.setPermissionID(permissions.stream().findFirst().get().getId());
//            rolePermissionID.setRoleID(roles.stream().findFirst().get().getId());
//
//            rolePermission.setRolePermissionID(rolePermissionID);
//            rolePermission.setAppPermission(permissions.stream().findFirst().get());
//            rolePermission.setAppRolePermission(roles.stream().findFirst().get());

//            rolePermissionRepo.save(rolePermission);



//            System.out.println(customers.stream().findFirst().get().getCustomerRoleIDS().stream().findFirst().get().getAppRole().getRolePermissions().stream().findFirst().get().getAppPermission().getPermissionName().name());
//
//
//            System.out.println(roles.stream().findFirst().get().getCustomerRoleIDS().stream().findFirst().get().getCustomer().getUserName());

//


//            CustomerRole customerRole = new CustomerRole();
//            CustomerRoleID customerRoleID =     new CustomerRoleID();
//            customerRoleID.setCustomerId(customers.stream().findFirst().get().getId());
//            customerRoleID.setAppRoleID(roles.stream().findFirst().get().getId());
//
//
//            customerRole.setCustomerRoleID(customerRoleID);
//            customerRole.setAppRole(roles.stream().findFirst().get());
//            customerRole.setCustomer(customers.stream().findFirst().get());
//
//            customerRoleRepo.save(customerRole);


//     customer has N Roles / roles belong to many customer




        };
    }

}
