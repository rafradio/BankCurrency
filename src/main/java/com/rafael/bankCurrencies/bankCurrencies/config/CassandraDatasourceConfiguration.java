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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

@Configuration
@EnableCassandraRepositories(
  basePackages = "com.rafael.bankCurrencies.bankCurrencies.cassandra.dao"
)
public class CassandraDatasourceConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties("spring.cassandra")
    public DataSourceProperties cassandraDataSourceProperties() {
        return new DataSourceProperties();
    }
    
}