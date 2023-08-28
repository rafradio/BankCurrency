package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.dao.ClientRepository;
import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import static org.junit.Assert.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;

@TestMethodOrder(OrderAnnotation.class)
public class CheckClientDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private ClientRepository clientRepository;
    
    @Value("${spring.property}")
    private String propertyString;
    
    @Test
    @Order(1) 
    @Override
    public void insertObjectToRepository() {
        this.insertClient();
        Optional<Client> clientResponse =  clientRepository.findById(1L);
        Client clnt = clientResponse.get();
        assertEquals("First test on bankAccountNumber","qwertweo57",clnt.getBankAccountNumber());
    }
    
    @Test
    @Order(2) 
    public void timeInsertingObjectToRepository() {
        Optional<Client> clientResponse =  clientRepository.findById(1L);
        LocalDateTime created = clientResponse.get().getCreated();
        Duration duration = Duration.between(created, LocalDateTime.now());
        long diff = Math.abs(duration.toSeconds());
        assertTrue("Client time created test", diff <= 10);
    }
    
    @Test
    @Order(3) 
    @Override
    public void deleteObjectFromRepository() {
        
    }
    
    public void insertClient() {
        Client client2 = Client.builder()
                .bankAccountNumber("qwertweo57")
                .transactions(new ArrayList<>())
                .allLimits(new ArrayList<>())
                .build();
        Client client1 = new Client(1L, LocalDateTime.now(), "qwertweo57", 
                new ArrayList<>(), new ArrayList<>());
        clientRepository.save(client2);
        
    }
    
    @Test
    @Order(4) 
    public void findProperty() {
        assertEquals("TEst Property","This the the application-test.yaml file", propertyString);
    }
    
    @Test
    @Order(5)
    public void ifTableExist() throws SQLException {
        DatabaseMetaData databaseMetaData = POSTGRE_SQL_CONTAINER.createConnection("").getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
        
        while (resultSet.next()) {
            String name = resultSet.getString("TABLE_NAME");
            String schema = resultSet.getString("TABLE_SCHEM");
            System.out.println(name + " on schema " + schema);
        }
        
        System.out.println(" on schema " + POSTGRE_SQL_CONTAINER.getJdbcUrl());
    }
}