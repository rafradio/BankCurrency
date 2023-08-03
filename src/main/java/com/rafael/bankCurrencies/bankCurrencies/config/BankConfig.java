package com.rafael.bankCurrencies.bankCurrencies.config;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:application.yml")
public class BankConfig {
    public final Environment environment;

    @Autowired
    public BankConfig(Environment environment) {
        this.environment = environment;
    }
    
}