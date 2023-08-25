package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.dao.ClientRepository;
import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.data.domain.Slice;

public class CheckClientDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private ClientRepository clientRepository;
    
    @Test
    @Override
    public void saveRecords() {
        Client client1 = new Client(3L, LocalDateTime.now(), "qwertweo57", 
                new ArrayList<>(), new ArrayList<>());
        clientRepository.save(client1);
        Optional<Client> clientResponse =  clientRepository.findById(1L);
        Client clnt = clientResponse.get();
        assertThat(clnt.getBankAccountNumber()).as("First test on bankAccountNumber").isEqualTo("qwertweo57");
    }
    
    @Test
    @Override
    public void deleteRecord() {
        
    }
    
}