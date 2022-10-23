package com.example.vantazii.core.security.auth;


import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.core.security.auth.DTO.GenerateOtpDto;
import com.example.vantazii.core.security.auth.DTO.GenerateTokenDto;
import com.example.vantazii.core.security.auth.DTO.NewCustomerResponse;
import com.example.vantazii.core.security.auth.response.JwtResponse;
import com.example.vantazii.core.security.jwt.JwtTokenService;
import com.example.vantazii.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//AppA Authntication Controller
@RestController
@RequestMapping("api/v1")
@CrossOrigin
@RequiredArgsConstructor
public class AppAuthenticationController {


    private final AuthenticationManager authenticationManager;


    private final JwtTokenService jwtTokenUtil;


    private final ApplicationUserDetailsService userDetailsService;

    private final AppOtpService appOtpService;



    @PostMapping(path = "/auth/otp/generate")
    public ResponseEntity<?> generateOtp(@Valid  @RequestBody GenerateOtpDto generateOtpDto) throws Exception {
        appOtpService.generateOtp(generateOtpDto.getPhoneNumber());
        if (userDetailsService.customerExist(generateOtpDto.getPhoneNumber())){
            return  ResponseEntity.ok(new NewCustomerResponse("Sms code sent success",false));
        }else {
           throw new ApiRequestException("Customer is new new onboarding need to be create", ApiExceptionType.NEW_CUSTOMER_TO_APP);
        }
    }


    @PostMapping(path = "/auth/token")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody GenerateTokenDto generateTokenDto) throws Exception {
       String savedCode =  appOtpService.getCacheOtp(generateTokenDto.getPhoneNumber());
       if (savedCode.equals(generateTokenDto.getToken())){
           final Authentication auth = authenticate(generateTokenDto.getPhoneNumber());
           SecurityContextHolder.getContext().setAuthentication(auth);
           Customer customer = userDetailsService.customerByPhone(generateTokenDto.getPhoneNumber());
           return ResponseEntity.ok(new JwtResponse(jwtTokenUtil.generateToken(auth),customer));
       }else {
           throw new ApiRequestException("Inccorect code please try again", ApiExceptionType.DEFAULT);
       }
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> saveUser(@RequestBody Customer user) throws Exception {
        return ResponseEntity.ok(userDetailsService.saveUser(user));
    }

    @GetMapping(path = "/user")
    public ResponseEntity<?> findUser(Authentication authentication) throws Exception {

        return ResponseEntity.ok(authentication.getPrincipal());
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
