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

public class TransactionDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Test
    @Override
    public void insertObjectToRepository() {
        this.insertTransaction();
        Optional<Transaction> transactionResponse =  transactionRepository.findById(1L);
        Transaction trn = transactionResponse.get();
        assertEquals("First test on Limit", "qwertweo16",trn.getAccountFrom());
    }
    
    @Test
    @Override
    public void deleteObjectFromRepository() {
        
    }
    
    public void insertTransaction() {
        Transaction transact1 = new Transaction(1L, LocalDateTime.now(), "qwertweo16", "qwertweo15",
            new BigDecimal("135.69"), "USD", "USD", false, 
            new Client(1L, LocalDateTime.now(), "qwertweo57", new ArrayList<>(), new ArrayList<>()), 
            new Limit(1L, LocalDateTime.now(), new BigDecimal("135.69"), new BigDecimal("135.69"), "USD", "USD", new Client(), new ArrayList<>()));
        transactionRepository.save(transact1);
    }
    
}
