package com.example.vantazii.core.config;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
@AllArgsConstructor
public class OpenFeignConfig {


    private FootBallConfig footBallConfig;
    @Bean
    public RequestInterceptor requestInterceptor(){
        return  new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header("X-RapidAPI-Key",footBallConfig.getXRapidApiKey());
//                requestTemplate.header("X-RapidAPI-Host",footBallConfig.getXRapidApiHost());
            }
        };
    }
}
