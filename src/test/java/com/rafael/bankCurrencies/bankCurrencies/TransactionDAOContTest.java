package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.dao.TransactionRepository;
import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import com.rafael.bankCurrencies.bankCurrencies.models.Limits;
import com.rafael.bankCurrencies.bankCurrencies.models.Transaction;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Test
    @Override
    public void saveRecords() {
        Transaction transact1 = new Transaction(5L, LocalDateTime.now(), "qwertweo16", "qwertweo15",
                new BigDecimal("135.69"), "USD", "USD", false, 
                new Client(1L, LocalDateTime.now(), "qwertweo57", new ArrayList<>(), new ArrayList<>()), 
                new Limits(1L, LocalDateTime.now(), new BigDecimal("135.69"), new BigDecimal("135.69"), "USD", "USD", new Client(), new ArrayList<>()));
        transactionRepository.save(transact1);
    }
    
    @Test
    @Override
    public void deleteRecord() {
        
    }
    
}
