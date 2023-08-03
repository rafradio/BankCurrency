package com.rafael.bankCurrencies.bankCurrencies.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;

@Configuration
public class MysqlDatasourceConfiguration {
    @Bean(name="mysqlDB")
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties postgresDataSourceProperties() {
        System.out.println("New world");
        return new DataSourceProperties();
    }
}