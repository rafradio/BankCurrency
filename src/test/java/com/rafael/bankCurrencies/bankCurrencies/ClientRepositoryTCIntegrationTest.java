package com.rafael.bankCurrencies.bankCurrencies;

//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import com.github.dockerjava.api.model.ExposedPort;
//import com.github.dockerjava.api.model.HostConfig;
//import com.github.dockerjava.api.model.PortBinding;
//import com.github.dockerjava.api.model.Ports;
//import com.rafael.bankCurrencies.bankCurrencies.dao.ClientRepository;
//import com.rafael.bankCurrencies.bankCurrencies.models.Client;
//import org.junit.ClassRule;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.util.TestPropertyValues;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Testcontainers
//@ContextConfiguration(initializers = {ClientRepositoryTCIntegrationTest.Initializer.class})
//public abstract class ClientRepositoryTCIntegrationTest {
//    
//    public static final int CONTAINER_PORT = 5432;
//    public static final int LOCALPORT = 5532;
//    public static final DockerImageName postgres = DockerImageName.parse("postgres:15.4");
//    
//    @ClassRule
//    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>(postgres)
//            .withDatabaseName("bank")
//            .withUsername("postgres")
//            .withPassword("postgres")
//            .withExposedPorts(CONTAINER_PORT)
//            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
//                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(LOCALPORT), new ExposedPort(CONTAINER_PORT)))
//            ));
//
//    static {
//        postgreSQLContainer.start();
//    }
//    
//    static class Initializer
//            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//                @Override
//                public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//                    TestPropertyValues.of(
//                        "spring.test.database.replace=none",
//                        "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
//                        "spring.datasource.username=" + postgreSQLContainer.getUsername(),
//                        "spring.datasource.password=" + postgreSQLContainer.getPassword()
//                      ).applyTo(configurableApplicationContext.getEnvironment());
//                }
//    }
//
//}