package com.rafael.bankCurrencies;

import com.rafael.bankCurrencies.repository.postgres.ClientRepository;
import com.rafael.bankCurrencies.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@ActiveProfiles(value = "test")
public abstract class AbstractTest {
    private static final String KEYSPACE_NAME = "bank_currencies";
    private static final int POSTGRES_CONTAINER_PORT = 5432;
    private static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:15.4");

    @Autowired
    protected ClientRepository clientRepository;

    protected static final Client CLIENT_1 = Client.builder()
            .bankAccountNumber("0000011111")
            .build();

    @Container
    public static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(POSTGRES_IMAGE)
            .withDatabaseName("bank")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(POSTGRES_CONTAINER_PORT);

    @Container
    public static final CassandraContainer<?> CASSANDRA_CONTAINER = (CassandraContainer<?>) new CassandraContainer("cassandra:latest")
            .withInitScript("cassandra/init.cql")
            .withExposedPorts(9042);

    static {
        POSTGRES_CONTAINER.start();
        CASSANDRA_CONTAINER.start();
    }

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