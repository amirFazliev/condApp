package com.example.condapp.config;

import com.example.condapp.profiles.DevProfile;
import com.example.condapp.profiles.ProductionProfile;
import com.example.condapp.profiles.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class JavaConfig {


    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "prod")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}