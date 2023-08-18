package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.dao.ClientRepository;
import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

//@Testcontainers
//@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckClientDAOContTest extends DAOCrudeTests {
    @Autowired
    private ClientRepository clientRepository;
    
    
    @Test
    @Override
    public void saveDAO() {
        Client client1 = new Client(3L, LocalDateTime.now(), "qwertweo26", 
                new ArrayList<>(), new ArrayList<>());
        clientRepository.save(client1);
    }
    
    
}