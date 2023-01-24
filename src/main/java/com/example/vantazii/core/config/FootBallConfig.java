package com.example.vantazii.core.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("football")
@Data
public class FootBallConfig {
    String xRapidApiKey;
    String xRapidApiHost;
}
