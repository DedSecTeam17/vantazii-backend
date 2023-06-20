package com.example.vantazii.customer;

import com.example.vantazii.core.config.FileManagmentConfig;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.customer.dto.CustomerDto;
import com.example.vantazii.fileManagment.FileManagmentService;
import com.example.vantazii.league.League;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    CustomerRepo customerRepo;

    private final FileManagmentService fileManagmentService;

    private FileManagmentConfig fileManagmentConfig;


    public Customer updateCustomer(CustomerDto customerDto){
        Optional<Customer> existingCustomer = customerRepo.findCustomerByPhoneNumber(customerDto.getPhoneNumber());


        if (existingCustomer.isPresent()){
            Customer customer = existingCustomer.get();

            customer.setEmail(customerDto.getEmail());
            customer.setUserName(customerDto.getUserName());
            String fileUrl = fileManagmentService.uploadFile(customerDto.getProfileImage());
            customer.setProfileImageUrl(fileUrl);
            return customerWithFullProfileImage(customerRepo.save(customer));

        }else {
            throw new ApiRequestException("Customer with this phone number not found", ApiExceptionType.NOT_FOUND);
        }
    }


    public Customer saveNewCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setCreatedAt(LocalDateTime.now());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        return customerRepo.save(customer);
    }


    public Customer updateCustomerToVerfied(Customer customer){

        customer.setVerified(true);
        return customerRepo.save(customer);
    }


    Customer customerWithFullProfileImage(Customer customer){
        customer.setProfileImageUrl(fileManagmentConfig.getPathToPreviewFiles()+customer.getProfileImageUrl());
        return  customer;
    }



}
