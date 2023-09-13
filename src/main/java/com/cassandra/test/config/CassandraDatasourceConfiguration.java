package com.cassandra.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.cassandra.test.repository.cassandra")
public class CassandraDatasourceConfiguration {
    
}
