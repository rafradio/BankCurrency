package com.rafael.bankCurrencies.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
        basePackages = "com.rafael.bankCurrencies.repository.cassandra"
)
@RequiredArgsConstructor
public class CassandraDatasourceConfiguration extends AbstractCassandraConfiguration {
    private final CassandraProperties cassandraProperties;

    @Override
    public String getKeyspaceName() {
        return cassandraProperties.getKeyspaceName();
    }

    @Bean
    public CassandraAdminTemplate cassandraTemplate() {
        return new CassandraAdminTemplate(super.getRequiredSessionFactory().getSession());
    }
}
