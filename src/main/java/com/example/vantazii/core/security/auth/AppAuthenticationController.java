package com.example.vantazii.core.security.auth;


import com.example.vantazii.CustomerRole.CustomerRoleService;
import com.example.vantazii.DTO.GlobalResponseBody;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.core.security.auth.DTO.GenerateOtpDto;
import com.example.vantazii.core.security.auth.DTO.GenerateTokenDto;
import com.example.vantazii.core.security.auth.DTO.NewCustomerResponse;
import com.example.vantazii.core.security.auth.response.JwtResponse;
import com.example.vantazii.core.security.jwt.JwtTokenService;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.customer.CustomerService;
import com.example.vantazii.customer.dto.CustomerDto;
import com.example.vantazii.role.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//AppA Authntication Controller
@RestController
@RequestMapping("api/v1")
@CrossOrigin
@RequiredArgsConstructor
public class AppAuthenticationController {


//    global response
/***
 * SUCESS --> TRUE
 * DATA --->
 * ERROR ---> {
 *     MESSAGE -->
 *
 *
 * }
 *
 *
 */



    private final AuthenticationManager authenticationManager;


    private final JwtTokenService jwtTokenUtil;


    private final ApplicationUserDetailsService userDetailsService;

    private final AppOtpService appOtpService;

    private final CustomerRoleService customerRoleService;


    private final CustomerService customerService;


    @PostMapping(path = "/auth/otp/generate")
    public ResponseEntity<?> generateOtp(@Valid @RequestBody GenerateOtpDto generateOtpDto) throws Exception {
        appOtpService.generateOtp(generateOtpDto.getPhoneNumber());

        boolean isOldCustomer  = userDetailsService.customerExist(generateOtpDto.getPhoneNumber());
        if (!isOldCustomer) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setPhoneNumber(generateOtpDto.getPhoneNumber());
            customerRoleService.createRoleFor( customerService.saveNewCustomer(customerDto), RoleName.CUSTOMER);
        }

        GlobalResponseBody<NewCustomerResponse> globalResponseBody = new  GlobalResponseBody<NewCustomerResponse>();
        globalResponseBody.setData(new NewCustomerResponse("Sms code sent success", !isOldCustomer));
        globalResponseBody.setStatus(true);
        return new ResponseEntity<>(globalResponseBody, HttpStatus.OK);

    }


    @PostMapping(path = "/auth/token")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody GenerateTokenDto generateTokenDto) throws Exception {
        String savedCode = appOtpService.getCacheOtp(generateTokenDto.getPhoneNumber());
        if (savedCode.equals(generateTokenDto.getToken())) {
            if (userDetailsService.customerExist(generateTokenDto.getPhoneNumber())) {
                final Authentication auth = authenticate(generateTokenDto.getPhoneNumber());
                SecurityContextHolder.getContext().setAuthentication(auth);
                Customer customer = userDetailsService.customerByPhone(generateTokenDto.getPhoneNumber());

                customerService.updateCustomerToVerfied(customer);

                GlobalResponseBody<JwtResponse> globalResponseBody = new  GlobalResponseBody<JwtResponse>();
                globalResponseBody.setData(new JwtResponse(jwtTokenUtil.generateToken(auth), customer));
                globalResponseBody.setStatus(true);
                return new ResponseEntity<>(globalResponseBody, HttpStatus.OK);

            } else {
                GlobalResponseBody<NewCustomerResponse> globalResponseBody = new  GlobalResponseBody<NewCustomerResponse>();
                globalResponseBody.setData(new NewCustomerResponse("Customer not found with phone number", true));
                globalResponseBody.setStatus(true);
                return new ResponseEntity<>(globalResponseBody, HttpStatus.OK);

            }

        } else {
            throw new ApiRequestException("Inccorect code please try again", ApiExceptionType.DEFAULT);
        }
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> saveUser(@Valid @ModelAttribute  CustomerDto customerDto) throws Exception {
        Customer savedCustomer = customerService.updateCustomer(customerDto);


        GlobalResponseBody<Customer> globalResponseBody = new  GlobalResponseBody<Customer>();
        globalResponseBody.setData(savedCustomer);
        globalResponseBody.setStatus(true);
        return new ResponseEntity<>(globalResponseBody, HttpStatus.OK);

    }

    @PostMapping(path = "/admin/register")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> saveUserAsAdmin(@RequestBody CustomerDto customerDto) throws Exception {
        Customer savedCustomer = customerService.updateCustomer(customerDto);

        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping(path = "/user/{phone}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_ADMIN') and hasAuthority('READ')")

    public ResponseEntity<?> findUserByPhone(@PathVariable String phone) throws Exception {

        return ResponseEntity.ok(userDetailsService.loadUserByUsername(phone));
    }

    @DeleteMapping(path = "/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') and hasAuthority('DELETE')")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(new Customer());
    }

    private Authentication authenticate(String phoneNumber) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, ""));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
