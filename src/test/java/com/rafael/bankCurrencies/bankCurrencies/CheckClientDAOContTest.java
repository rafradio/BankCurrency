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

public class CheckClientDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private ClientRepository clientRepository;
    
    @Test
    @Override
    public void ifTableExist() throws SQLException {
        DatabaseMetaData databaseMetaData = POSTGRE_SQL_CONTAINER.createConnection("").getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
        
        System.out.println("Connections" + databaseMetaData.getURL());
        while (resultSet.next()) {
            String name = resultSet.getString("TABLE_NAME");
            String schema = resultSet.getString("TABLE_SCHEM");
            System.out.println(name + " on schema " + schema);
        }
        
    }
    
    
    @Test
    @Override
    public void saveRecords() {
        Client client1 = new Client(3L, LocalDateTime.now(), "qwertweo57", 
                new ArrayList<>(), new ArrayList<>());
        clientRepository.save(client1);
        
    }
    
    
}