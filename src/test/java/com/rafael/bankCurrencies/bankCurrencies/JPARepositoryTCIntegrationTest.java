package com.rafael.bankCurrencies.bankCurrencies;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "test")
//@DataJpaTest
public abstract class JPARepositoryTCIntegrationTest {
    
    public static final int CONTAINER_PORT = 5432;
    public static final int LOCALPORT = 5532;
    public static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:15.4");
    
//    @Container
    public static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>(POSTGRES_IMAGE)
            .withDatabaseName("bank")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(CONTAINER_PORT)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(LOCALPORT), new ExposedPort(CONTAINER_PORT)))
            ));
    
    static {
        POSTGRE_SQL_CONTAINER.start();
    }
    
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        System.out.println(" on schema " + POSTGRE_SQL_CONTAINER.getJdbcUrl());
        registry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);
        
    }

}