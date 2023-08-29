package com.rafael.bankCurrencies.bankCurrencies;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;

@SpringBootTest
@Testcontainers
@ActiveProfiles(value = "test")
public class CassandraТСIntegrationTest {
    @Container
    public static final CassandraContainer cassandra 
      = (CassandraContainer) new CassandraContainer("cassandra:latest").withExposedPorts(9042);
    
//    @BeforeAll
//    static void setupCassandraConnectionProperties() {
//        System.out.println("Cassandra " + cassandra.getContactPoint());
//        System.setProperty("spring.datasource.cassandra.keyspace-name", "spring_cassandra");
//        System.setProperty("spring.datasource.cassandra.contact-points", cassandra.getContainerIpAddress());
//        System.setProperty("spring.datasource.cassandra.port", String.valueOf(cassandra.getMappedPort(9042)));
//    }
    
    private static void createKeyspace(Cluster cluster) {
        try (Session session = cluster.connect()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS " + "spring_cassandra" +
              " WITH replication = \n" +
              "{'class':'SimpleStrategy','replication_factor':'1'};");
        }
    }
    
    @DynamicPropertySource
    static void setupCassandraConnectionProperties(DynamicPropertyRegistry registry) {
//        cassandra.getContactPoint();
        registry.add("spring.cassandra.keyspace-name", "spring_cassandra"::toString);
        registry.add("spring.cassandra.contact-points", cassandra::getContactPoint);
        registry.add("spring.cassandra.port", String.valueOf(cassandra.getMappedPort(9042))::toString);
        registry.add("spring.cassandra.local-datacenter", "datacenter1"::toString);
        registry.add("spring.cassandra.schema-action", "create_if_not_exists"::toString);
        
        createKeyspace(cassandra.getCluster());
    }
    
    @Test
    void givenCassandraContainer_whenSpringContextIsBootstrapped_thenContainerIsRunningWithNoExceptions() {
        assertTrue(cassandra.isRunning());
//        assertThat(cassandra.isRunning()).isTrue();
    }
    
}
