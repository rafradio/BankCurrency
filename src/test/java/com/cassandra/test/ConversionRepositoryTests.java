package com.cassandra.test;

import static com.cassandra.test.AbstractTest.CLIENT_1;
import com.cassandra.test.models.Conversion;
import com.cassandra.test.repository.cassandra.ConversionRepository;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import com.zaxxer.hikari.HikariDataSource;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cassandra.CassandraConnectionDetails;
import org.springframework.context.ApplicationContext;

@TestMethodOrder(OrderAnnotation.class)
class ConversionRepositoryTests extends AbstractTest {
    @Autowired
    public ConversionRepository conversionRepository;
    
    @Autowired
    @Qualifier("testContext")
    private ApplicationContext testContext;
    
    private Conversion newConver = new Conversion(UUID.randomUUID(), new BigDecimal("135.69"), 
                new BigDecimal("135.69"),LocalDateTime.now(), "usd");
    
    @BeforeEach
    public void saveTestConversiontData() {
        conversionRepository.save(newConver);
    }
    
    @Test
    @Order(1)
    public void givenCassandraContainer_whenAppContextIsBootstrapped_thenContainerIsRunningWithNoExceptions() {
        assertTrue(CASSANDRA_CONTAINER.isRunning());
        CassandraConnectionDetails thisCassandraDetails = (CassandraConnectionDetails) this.testContext.getBean("cassandraConnectionDetails");
        assertEquals(CASSANDRA_CONTAINER.getHost(), thisCassandraDetails.getContactPoints().get(0).host(), "Test cassandra datasource");
        assertEquals(CASSANDRA_CONTAINER.getContactPoint().getPort(), thisCassandraDetails.getContactPoints().get(0).port(), "Test cassandra datasource");
    }
    
    @Test
    @Order(2)
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
    @Order(3)
    public void whenInsertToRepository_getConversionById_thenTrue() {
        conversionRepository.findById(this.newConver.getId())
            .ifPresentOrElse(
                cnvr -> {
                    assertEquals(this.newConver.getId(), cnvr.getId(), "Second test on Conversion");
                }, 
                () -> {
                    throw new IllegalStateException("The user wasn't found");
                });
    }
    
    @Test
    @Order(4)
    public void whenInsertToRepository_checkConversionTimeCreation_lessThanTenSec_thenTrue() {
        conversionRepository.findById(this.newConver.getId())
            .ifPresentOrElse(
                cnvr -> {
                    Duration duration = Duration.between(cnvr.getMadeAt(), LocalDateTime.now());
                    long diff = Math.abs(duration.toSeconds());
                    assertTrue(diff <= 10);
                }, 
                () -> {
                    throw new IllegalStateException("The user wasn't found");
                });
    }
    
    @Test
    @Order(5)
    public void whenUpdateRepository_conversion_thenTrue() {
        conversionRepository.findById(this.newConver.getId())
            .ifPresentOrElse(
                cnvr -> {
                    BigDecimal firstRateCreation = cnvr.getRate();
                    cnvr.setRate(new BigDecimal("145.69"));
                    BigDecimal diff = conversionRepository.save(cnvr).getRate().subtract(firstRateCreation);
                    BigDecimal forTest = new BigDecimal("10");
                    assertTrue(diff.compareTo(forTest) == 0);
                }, 
                () -> {
                    throw new IllegalStateException("The user wasn't found");
                });
    }

}