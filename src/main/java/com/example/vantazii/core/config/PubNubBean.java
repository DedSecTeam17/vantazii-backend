package com.example.vantazii.core.config;


import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubNubBean {


    private final PubNubConfig pubNubConfig;


    @Autowired
    public PubNubBean(PubNubConfig pubNubConfig) {
        this.pubNubConfig = pubNubConfig;
    }


    @Bean
    public PubNub pubNub() {
        final UserId userId = new UserId(pubNubConfig.getSecretkey());
        try {
            PNConfiguration    pnConfiguration = new PNConfiguration(userId);
            pnConfiguration.setSubscribeKey(pubNubConfig.getSubscribekey());
            pnConfiguration.setPublishKey(pubNubConfig.getPublishkey());

            PubNub pubNub = new PubNub(pnConfiguration);

            System.out.println("BASE URL FOR PUBNUB "+pubNub.getBaseUrl());
            System.out.println("PUBNUB VERSION :"+pubNub.getVersion());

            return  pubNub;
        } catch (PubNubException e) {
            throw new RuntimeException(e);
        }

    }

}
