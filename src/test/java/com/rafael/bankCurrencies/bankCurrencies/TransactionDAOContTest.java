package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.dao.TransactionRepository;
import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import com.rafael.bankCurrencies.bankCurrencies.models.Limits;
import com.rafael.bankCurrencies.bankCurrencies.models.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class TransactionDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Test
    @Override
    public void ifTableExist() {
        
    }
    
    @Test
    @Override
    public void saveRecords() {
        Transaction transact1 = new Transaction(5L, LocalDateTime.now(), "qwertweo16", "qwertweo15",
                new BigDecimal("135.69"), "USD", "USD", false, 
                new Client(12L, LocalDateTime.now(), "qwertweo10", new ArrayList<>(), new ArrayList<>()), 
                new Limits(3L, LocalDateTime.now(), new BigDecimal("135.69"), new BigDecimal("135.69"), "USD", "USD", new Client(), new ArrayList<>()));
        transactionRepository.save(transact1);
    }
    
}
