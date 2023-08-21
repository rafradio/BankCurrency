package com.rafael.bankCurrencies.bankCurrencies;

//import static com.rafael.bankCurrencies.bankCurrencies.ClientRepositoryTCIntegrationTest.CONTAINERPORT;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
public abstract class JPARepositoryTCIntegrationTest {
    
    public static final int CONTAINERPORT = 5432;
    public static final int LOCALPORT = 5532;
    public static final DockerImageName postgresImage = DockerImageName.parse("postgres:15.4");
    
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(postgresImage)
            .withDatabaseName("bank")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(CONTAINERPORT);
    
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", 
          () -> String.format("jdbc:postgresql://localhost:%d/bank", postgreSQLContainer.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "postgres");
        
    }
    
}