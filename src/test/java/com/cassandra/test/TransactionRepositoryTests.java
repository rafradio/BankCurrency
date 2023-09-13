package com.cassandra.test;

import static com.cassandra.test.AbstractTest.LIMIT_1;
import com.cassandra.test.models.Transaction;
import com.cassandra.test.repository.postgres.TransactionRepository;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
public class TransactionRepositoryTests extends AbstractTest {
    @Autowired
    TransactionRepository transactionRepository;
    
    private final Transaction TRANSACTION_1 = new Transaction("0000011111", "0000011111", new BigDecimal("2000"),
            "usd", "usd", false, CLIENT_1, LIMIT_1);
    
    @BeforeEach
    public void saveTestClientData() {
        transactionRepository.save(TRANSACTION_1);
    }
    
    @Test
    public void whenInsertToRepository_getTransactionById_thenTrue() {
        transactionRepository.findById(TRANSACTION_1.getId())
            .ifPresentOrElse(trn -> {
                assertEquals(TRANSACTION_1.getId(), trn.getId(), "First test on Transaction");
            }, () -> {
                throw new IllegalStateException("The transaction wasn't found");
            }); 
    }
    
    @Test
    public void whenInsertToRepository_checkTransactionTimeCreation_lessThanTenSec_thenTrue() {
        transactionRepository.findById(TRANSACTION_1.getId())
            .ifPresentOrElse(trn -> {
                assertEquals(TRANSACTION_1.getId(), trn.getId());
                Duration duration = Duration.between(trn.getCreated(), LocalDateTime.now());
                long diff = Math.abs(duration.toSeconds());
                assertTrue(diff <= 10);
            }, () -> {
                throw new IllegalStateException("The limit wasn't found");
            });
    }
}
