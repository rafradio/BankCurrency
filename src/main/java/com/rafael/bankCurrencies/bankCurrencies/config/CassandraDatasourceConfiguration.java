package com.rafael.bankCurrencies.bankCurrencies.config;

import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

@Configuration
@EnableTransactionManagement
@EnableCassandraRepositories(
  basePackages = "com.rafael.bankCurrencies.bankCurrencies.cassandra.dao"
)
public class CassandraDatasourceConfiguration  {
    
    @Bean
    @ConfigurationProperties("spring.cassandra")
    public DataSourceProperties cassandraDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean
    public DataSource cassandraDataSource() {
        return cassandraDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
    
//    @Bean
//    public CassandraMappingContext cassandraMapping() 
//      throws ClassNotFoundException {
//        return new BasicCassandraMappingContext();
//    }
    
//    @Bean
//    public LocalContainerEntityManagerFactoryBean cassandraEntityManagerFactory(
//            @Qualifier("cassandraDataSource") DataSource dataSource,
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.rafael.bankCurrencies.bankCurrencies.cassandra.dao")
//                .build();
//    }
    
    
    
}
