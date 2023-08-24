package com.rafael.bankCurrencies.bankCurrencies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
public class DatabasesDatasourceConfiguration {
    @Autowired
    private Environment environment;
    
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties () {
        return new DataSourceProperties();
    }
    
    @Bean
    @ConfigurationProperties("spring.cassandra")
    public DataSourceProperties cassandraDataSourceProperties() {
        return new DataSourceProperties();
    }
}