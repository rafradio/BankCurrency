package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import com.rafael.bankCurrencies.bankCurrencies.models.Limits;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.rafael.bankCurrencies.bankCurrencies.dao.LimitsRepository;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@SpringBootTest
public class LimitsDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private LimitsRepository limitsRepository;
    
    @Test
    @Override
    public void ifTableExist() {
        
    }
    
    @Test
    @Override
    public void saveRecords() {
        Limits limit1 = new Limits(6L, LocalDateTime.now(), new BigDecimal("135.69"), 
                new BigDecimal("135.69"), "USD", "USD", 
                new Client(12L, LocalDateTime.now(), "qwertweo10", new ArrayList<>(), new ArrayList<>()),
                new ArrayList<>());
        limitsRepository.save(limit1);
    }
}