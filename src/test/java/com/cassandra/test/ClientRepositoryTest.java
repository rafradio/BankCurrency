package com.cassandra.test;

import static com.cassandra.test.AbstractTest.CASSANDRA_CONTAINER;
import com.cassandra.test.models.Client;
import com.cassandra.test.repository.postgres.ClientRepository;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;

public class ClientRepositoryTest extends AbstractTest {
    @Autowired
    ClientRepository clientRepository;
    
    public Client saveTestData() {
        return clientRepository.save(CLIENT_1);
    }
    
    @Test
    public void givenPostgresContainer_whenSpringContextIsBootstrapped_thenContainerIsRunningWithNoExceptions() {
        assertTrue(POSTGRES_CONTAINER.isRunning());
    }
    
    @Test
    public void whenPostGresContainerStart_LiquibaseCreateClientEntity_thenTrue() throws SQLException {
        DatabaseMetaData databaseMetaData = POSTGRES_CONTAINER.createConnection("").getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
        ArrayList<String> arrTables = new ArrayList<>();
        while (resultSet.next()) {
            arrTables.add(resultSet.getString("TABLE_NAME"));
        }
        boolean columnExist = arrTables.stream().anyMatch(p -> p.equals("client"));
        assertTrue(columnExist);
    }
    
    @Test
    public void whenInsertToRepository_getClientById_thenTrue() {
        Client cln = this.saveTestData();
        Optional<Client> clientResponse =  clientRepository.findById(cln.getId());
        clientResponse.ifPresent(cl -> {
            assertEquals(cln.getId(), cl.getId(), "Second test on Client");
        });
    }
}
