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
public class CheckClientDAOContTest extends JPARepositoryTCIntegrationTest {
    @Autowired
    private ClientRepository clientRepository;
    
    @Test
    @Order(1) 
    public void whenInsertToRepository_clientCheckID_thenTrue() {
        Client client1 = this.insertToRepository_Client();
        Optional<Client> clientResponse =  clientRepository.findById(client1.getId());
        clientResponse.ifPresent(client -> {
                assertEquals("First test on bankAccountNumber",client1.getBankAccountNumber(),
                                                                       client.getBankAccountNumber());
        });
    }
    
    @Test
    @Order(2) 
    public void whenSavingTime_clientLessThanTen_thenTrue() {
        Optional<Client> clientResponse =  clientRepository.findById(1L);
        LocalDateTime created = clientResponse.get().getCreated();
        Duration duration = Duration.between(created, LocalDateTime.now());
        long diff = Math.abs(duration.toSeconds());
        assertTrue("Client time created test", diff <= 10);
    }
    
    @Test
    @Order(3) 
    public void whenDeleted_clientEntity_thenTrue() {
    }
    
    public Client insertToRepository_Client() {
        Client client2 = Client.builder()
                .bankAccountNumber("qwertweo57")
                .transactions(new ArrayList<>())
                .allLimits(new ArrayList<>())
                .build();
        return clientRepository.save(client2);
    }
    
}