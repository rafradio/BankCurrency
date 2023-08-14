package com.rafael.bankCurrencies.bankCurrencies.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;

public class CassandraDatasourceConfiguration {
    @Qualifier
    @Bean(name="cassandraDB")
    @ConfigurationProperties("spring.cassandra")
    public DataSourceProperties postgresDataSourceProperties() {
        System.out.println("Cassandra world");
        return new DataSourceProperties();
    }
}
