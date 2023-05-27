package com.example.condapp.config;

import com.example.condapp.profiles.DevProfile;
import com.example.condapp.profiles.ProductionProfile;
import com.example.condapp.profiles.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "profile", value = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "prod")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}