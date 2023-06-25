package com.example.vantazii.core.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pubnub")
@Data
public class PubNubConfig {
    private String publishkey;
    private String subscribekey;
    private String secretkey;

}
