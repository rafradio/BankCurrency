package com.rafael.bankCurrencies.bankCurrencies.config;

//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
////import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
//@Configuration
//public class DatabasesDatasourceConfiguration {
//    
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.datasource.postgres")
//    public DataSourceProperties postgresDataSourceProperties () {
//        return new DataSourceProperties();
//    }
//    
//    @Bean
//    @ConfigurationProperties("spring.cassandra")
//    public DataSourceProperties cassandraDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//    
//    @Bean
//    public DataSource postgresDataSource() {
//        return postgresDataSourceProperties()
//          .initializeDataSourceBuilder()
//          .build();
//    }
//    
//    @Bean
//    public DataSource cassandraDataSource() {
//        return cassandraDataSourceProperties()
//          .initializeDataSourceBuilder()
//          .build();
//    }
//}