package com.rafael.bankCurrencies.bankCurrencies.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PostgresDatasourceConfiguration {
    @Primary
    @Bean(name="postgresDB")
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties() {
        System.out.println("Hello world");
        return new DataSourceProperties();
    }
}