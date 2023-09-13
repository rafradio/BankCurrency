package com.cassandra.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
  basePackages = {"com.cassandra.test.repository.postgres"}
)
public class PostgresDatasourceConfiguration {
    
}
