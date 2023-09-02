package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.dao.TransactionRepository;
import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import com.rafael.bankCurrencies.bankCurrencies.models.Limit;
import com.rafael.bankCurrencies.bankCurrencies.models.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

public class TransactionDAOContTest extends JPARepositoryTCIntegrationTest {
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Test
    public void whenInsertToRepository_transactionCheckID_thenTrue() {
        Transaction transaction = this.insertToRepository_Transaction();
        Optional<Transaction> transactionResponse =  transactionRepository.findById(transaction.getId());
        transactionResponse.ifPresent(trn -> {
            assertEquals("First test on Limit", transaction.getAccountFrom(),trn.getAccountFrom());
        });
    }
    
    @Test
    public void whenDeleted_transactionEntity_thenTrue() {
        
    }
    
    public Transaction insertToRepository_Transaction() {
        Transaction transact1 = new Transaction("qwertweo16", "qwertweo15",
            new BigDecimal("135.69"), "USD", "USD", false, 
            new Client(1L, LocalDateTime.now(), "qwertweo57", new ArrayList<>(), new ArrayList<>()), 
            new Limit(new BigDecimal("135.69"), new BigDecimal("135.69"), "USD", "USD", new Client(), new ArrayList<>()));
        return transactionRepository.save(transact1);
    }
    
}