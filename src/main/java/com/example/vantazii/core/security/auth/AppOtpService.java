package com.example.vantazii.core.security.auth;


import com.example.vantazii.core.config.TwillioConfig;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Service
public class AppOtpService {
    private static final  Integer EXPIRE_MIN = 5;
    private LoadingCache<String,String> otpCache;
    @Autowired
    private TwillioConfig twilioConfig;

    public AppOtpService() {
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public String load(String s) {
                        return "";
                    }
                });
    }

    public String generateOtp(String phoneNo){
        PhoneNumber to = new PhoneNumber(phoneNo);
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
        String otp = getRandomOTP(phoneNo);
        String otpMessage = "Dear Customer , Your OTP is " + otp + ". Use this otp to log in to vantazii Application";
//        Message message = Message
//                .creator(to, from,
//                        otpMessage)
//                .create();
        return  otp;
    }

    private String getRandomOTP(String phoneNo) {
        String otp =  new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
        System.out.println(otp);
        otpCache.put(phoneNo,"0000");
        return "0000";
    }
    //get saved otp
    public String getCacheOtp(String key){
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return "";
        }
    }
    //clear stored otp
    public void clearOtp(String key){
        otpCache.invalidate(key);
    }
}
