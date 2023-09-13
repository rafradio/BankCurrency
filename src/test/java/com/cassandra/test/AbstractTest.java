package com.cassandra.test;

import com.cassandra.test.models.Client;
import com.cassandra.test.models.Limit;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles(value = "test")
@Testcontainers
public abstract class AbstractTest {
    private static final String KEYSPACE_NAME = "bank_currencies";
    private static final int POSTGRES_CONTAINER_PORT = 5432;
    private static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:15.4");
    
    protected static final Client CLIENT_1 = Client.builder()
            .bankAccountNumber("0000011111")
            .build();
    
    protected static final Limit LIMIT_1 = new Limit(new BigDecimal("135.69"), 
            new BigDecimal("135.69"), "USD", "USD", 
            CLIENT_1,
            new ArrayList<>());
    
    public static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(POSTGRES_IMAGE)
            .withDatabaseName("bank")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(POSTGRES_CONTAINER_PORT);
    
//    @Container
    public static final CassandraContainer<?> CASSANDRA_CONTAINER = (CassandraContainer<?>) new CassandraContainer("cassandra:latest")
            .withInitScript("cassandra/init.cql")
            .withExposedPorts(9042);

    static {
        POSTGRES_CONTAINER.start();
        CASSANDRA_CONTAINER.start();
    }
    
//    @BeforeAll
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);

        registry.add("spring.cassandra.contact-points", CASSANDRA_CONTAINER::getHost);
        registry.add("spring.cassandra.datacenter", CASSANDRA_CONTAINER::getLocalDatacenter);
        registry.add("spring.cassandra.keyspace-name", () -> KEYSPACE_NAME);
        registry.add("spring.cassandra.port", () -> CASSANDRA_CONTAINER.getMappedPort(9042));
    }
    
}
