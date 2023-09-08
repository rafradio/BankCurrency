package com.cassandra.test;

import com.cassandra.test.models.Conversion;
import com.cassandra.test.repository.cassandra.ConversionRepository;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;

class ConversionRepositoryTests extends AbstractTest {
    @Autowired
    public ConversionRepository conversionRepository;

    @Value("${spring.profile.property.value}")
    private String propertyString;
    
    @Test
    void contextLoads() {
    }
    
    @Test
    public void whenTestIsActive_thenValueShouldBeKeptFromApplicationYaml() {
        assertEquals("Hello world",propertyString,"Test profile");
    }
    
    @Test
    void givenCassandraContainer_whenSpringContextIsBootstrapped_thenContainerIsRunningWithNoExceptions() {
        assertTrue(CASSANDRA_CONTAINER.isRunning());
    }
    
    @Test
    public void whenCassandraTable_Created_thenTrue() {
        Cluster cluster = CASSANDRA_CONTAINER.getCluster();
//        Session session = CASSANDRA_CONTAINER.getCluster().newSession();
        Session session = cluster.connect();
        ResultSet result = session.execute("SELECT * FROM bank_currencies" + "." + "conversion" + ";");
        List<String> columnNames = result.getColumnDefinitions().asList().stream()
                .map(cl -> cl.getName()).collect(Collectors.toList());
        boolean columnExist = columnNames.stream().anyMatch(p -> p.equals("rateonpreviousclose"));
        session.close();
        assertTrue(columnExist);
    }
    
    @Test
    public void whenInsertToRepository_getConversionById_thenTrue() {
        Conversion newConver = new Conversion(UUID.randomUUID(), new BigDecimal("135.69"), 
                new BigDecimal("135.69"), LocalDateTime.now(), "usd");
        Conversion newConver1 = conversionRepository.save(newConver);
        Optional<Conversion> limitResponse =  conversionRepository.findById(newConver1.getId());
        limitResponse.ifPresent(cnvr -> {
            assertEquals(newConver1.getId(), cnvr.getId(), "Second test on Conversion");
        });
    }
    

}
