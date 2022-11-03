package com.example.vantazii.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "msg")
@Data
public class MessgingQueueConfig {
    private String ququeExchange;
    private String ququeName;
    private String ququeRouting;

}
