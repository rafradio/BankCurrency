package com.cassandra.test;

import static com.cassandra.test.AbstractTest.CASSANDRA_CONTAINER;
import com.cassandra.test.models.Client;
import com.cassandra.test.repository.postgres.ClientRepository;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Streamable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.DisabledIf;
//import org.junitpioneer.jupiter.DisableIfTestFailsExtension;

@DirtiesContext
//@DisableIfTestFails
@TestMethodOrder(OrderAnnotation.class)
public class ClientRepositoryTest extends AbstractTest {
    @Autowired
    ClientRepository clientRepository;
    
    @BeforeEach
    public void saveTestClientData() {
        clientRepository.save(CLIENT_1);
    }
    
    @Autowired
    @Qualifier("testContext")
    private ApplicationContext testContext;
    
    boolean clientTestConditionalFunction() {
        return true;
    }
    
    @Test
    @Order(1)
    public void givenPostgresContainer_whenSpringContextIsBootstrapped_thenContainerIsRunningWithNoExceptions() {
        assertTrue(POSTGRES_CONTAINER.isRunning());
        HikariDataSource postgressDataSource = (HikariDataSource) testContext.getBean("dataSource");
        assertEquals(POSTGRES_CONTAINER.getJdbcUrl(), postgressDataSource.getJdbcUrl(), 
                "First Client test");
//        this.clientTestConditionalFunction();
    }
    
    @Test
    @Order(2)
    public void whenPostGresContainerStart_LiquibaseCreateClientEntity_thenTrue() throws SQLException {
        DatabaseMetaData databaseMetaData = POSTGRES_CONTAINER.createConnection("").getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
        ArrayList<String> arrTables = new ArrayList<>();
        while (resultSet.next()) {
            arrTables.add(resultSet.getString("TABLE_NAME"));
        }
        boolean tableExist = arrTables.stream().anyMatch(p -> p.equals("client"));
        assertTrue(tableExist);
    }
    
    @Test
    @Order(3)
    @DisabledIf("clientTestConditionalFunction")
    public void whenInsertToRepository_getClientById_thenTrue() {
//        Optional<Client> clientResponse = clientRepository.findById(CLIENT_1.getId());
        clientRepository.findById(CLIENT_1.getId())
            .ifPresentOrElse(cl -> {
                assertEquals(CLIENT_1.getId(), cl.getId(), "First test on Client");
            }, () -> {
                throw new IllegalStateException("The user wasn't found");
            });
    }
    
    public Integer returnSizeOfClients() {
        Iterable<Client> clnList = clientRepository.findAll();
        return Streamable.of(clnList).toList().size();
    }
    
    @Test
    @Order(4)
    @DisabledIf("clientTestConditionalFunction")
//    @DisabledIf("#{clientTestConditionalFunction()}")
    public void whenInsertToRepository_checkClientTimeCreation_lessThanTenSec_thenTrue() {
        clientRepository.findById(CLIENT_1.getId())
                .ifPresentOrElse(client -> {
                    assertEquals(CLIENT_1.getBankAccountNumber(), client.getBankAccountNumber());
                    Duration duration = Duration.between(client.getCreated(), LocalDateTime.now());
                    long diff = Math.abs(duration.toSeconds());
                    assertTrue(diff <= 10);
                }, () -> {
                    throw new IllegalStateException("The user wasn't found");
                });
    }
    
    @Test
    @Order(5)
    @DisabledIf("clientTestConditionalFunction")
    public void whenDeleteRepository_checkClient_thenTrue() {
        clientRepository.delete(CLIENT_1);
        clientRepository.findById(CLIENT_1.getId())
            .ifPresentOrElse(client -> {
                assertTrue(false);
            }, () -> {
                assertTrue(true);
            });
        
    }
    
    @Test
    @Order(6)
    @DisabledIf("clientTestConditionalFunction")
    public void whenUpdateRepository_checkClientTimeUpdate_thenTrue() {
        clientRepository.findById(CLIENT_1.getId())
            .ifPresentOrElse(client -> {
                LocalDateTime firstTimeCreation = client.getCreated();
                client.setCreated(LocalDateTime.now());
                LocalDateTime secontTimeUpdated = clientRepository.save(client).getCreated();
                Duration duration = Duration.between(firstTimeCreation, secontTimeUpdated);
                long diff = Math.abs(duration.toMillis());
                assertTrue(diff > 0);
            }, () -> {
                throw new IllegalStateException("The user wasn't found");
            });
    }
    
}