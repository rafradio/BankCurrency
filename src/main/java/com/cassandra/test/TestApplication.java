package com.cassandra.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCassandraRepositories(basePackages = "com.cassandra.test.repository.cassandra")
@EnableJpaRepositories(
  basePackages = {"com.cassandra.test.repository.postgres"}
)
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
