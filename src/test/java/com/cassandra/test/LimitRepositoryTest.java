package com.cassandra.test;

import static com.cassandra.test.AbstractTest.CLIENT_1;
import com.cassandra.test.config.PostgresDatasourceConfiguration;
//import com.cassandra.test.config.ApplictionContestProvider;
import com.cassandra.test.models.Limit;
import com.cassandra.test.repository.postgres.LimitRepository;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
public class LimitRepositoryTest extends AbstractTest {
    
    @Autowired
    LimitRepository limitRepository;
    
//    private final Limit LIMIT_1 = new Limit(new BigDecimal("135.69"), 
//            new BigDecimal("135.69"), "USD", "USD", 
//            CLIENT_1,
//            new ArrayList<>());
    
    @BeforeEach
    public void saveTestClientData() {
        limitRepository.save(LIMIT_1);
    }
    
    @Test
    public void whenInsertToRepository_getLimitById_thenTrue() {
        limitRepository.findById(LIMIT_1.getId())
            .ifPresentOrElse(lm -> {
                assertEquals(LIMIT_1.getId(), lm.getId(), "First test on Limit");
            }, () -> {
                throw new IllegalStateException("The limit wasn't found");
            }); 
    }
    
    @Test
    public void whenInsertToRepository_checkLimitTimeCreation_lessThanTenSec_thenTrue() {
        limitRepository.findById(LIMIT_1.getId())
            .ifPresentOrElse(limit -> {
                assertEquals(LIMIT_1.getId(), limit.getId());
                Duration duration = Duration.between(limit.getCreated(), LocalDateTime.now());
                long diff = Math.abs(duration.toSeconds());
                assertTrue(diff <= 10);
            }, () -> {
                throw new IllegalStateException("The limit wasn't found");
            });
    }
}