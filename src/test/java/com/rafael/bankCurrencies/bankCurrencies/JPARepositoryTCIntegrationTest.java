package com.rafael.bankCurrencies.bankCurrencies;

//import com.datastax.driver.core.Cluster;
//import com.datastax.driver.core.Session;
//import com.github.dockerjava.api.model.ExposedPort;
//import com.github.dockerjava.api.model.HostConfig;
//import com.github.dockerjava.api.model.PortBinding;
//import com.github.dockerjava.api.model.Ports;
//import org.junit.jupiter.api.BeforeAll;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.CassandraContainer;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import javax.sql.DataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Testcontainers
@ActiveProfiles(value = "test")
public abstract class JPARepositoryTCIntegrationTest {
    
    public static final int CONTAINER_PORT = 5432;
    public static final int LOCALPORT = 5532;
    public static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:15.4");
    
    @Container
    public static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>(POSTGRES_IMAGE)
            .withDatabaseName("bank")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(CONTAINER_PORT)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(LOCALPORT), new ExposedPort(CONTAINER_PORT)))
            ));
    
    @Container
    public static final CassandraContainer cassandra 
      = (CassandraContainer) new CassandraContainer("cassandra:latest").withExposedPorts(9042);
    
    static {
        POSTGRE_SQL_CONTAINER.start();
    }
    
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        System.out.println(" on schema " + POSTGRE_SQL_CONTAINER.getJdbcUrl());
        registry.add("datasource.postgres.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("datasource.postgres.username", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("datasource.postgres.password", POSTGRE_SQL_CONTAINER::getPassword);
        
        registry.add("cass.contact-points", cassandra::getContainerIpAddress);
        registry.add("cass.keyspace-name", "spring_cassandra"::toString);
        Integer val1 = cassandra.getMappedPort(9042);
        registry.add("cass.port", val1::toString);
    }
    
    private static void createKeyspace(Cluster cluster) {
        try (Session session = cluster.connect()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS " + "spring_cassandra" +
              " WITH replication = \n" +
              "{'class':'SimpleStrategy','replication_factor':'1'};");
        }
    }
    
//    @BeforeAll
//    static void setupCassandraConnectionProperties() {
//        System.setProperty("cassandra.keyspace-name", "spring_cassandra");
//        System.setProperty("cassandra.contact-points", cassandra.getContainerIpAddress());
//        System.setProperty("cassandra.port", String.valueOf(cassandra.getMappedPort(9042)));
//
//    }

}