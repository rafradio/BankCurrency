package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import com.rafael.bankCurrencies.bankCurrencies.models.Limit;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.rafael.bankCurrencies.bankCurrencies.dao.LimitRepository;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

public class LimitsDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private LimitRepository limitRepository;
    
    @Test
    @Override
    public void insertObjectToRepository() {
        this.insertLimit();
        Optional<Limit> limitResponse =  limitRepository.findById(1L);
        Limit lmt = limitResponse.get();
        assertEquals("First test on Limit", Long.valueOf(1),lmt.getId());
        
    }
    
    @Test
    @Override
    public void deleteObjectFromRepository() {
        
    }
    
    public void insertLimit() {
        Limit limit1 = new Limit(6L, LocalDateTime.now(), new BigDecimal("135.69"), 
            new BigDecimal("135.69"), "USD", "USD", 
            new Client(1L, LocalDateTime.now(), "qwertweo57", new ArrayList<>(), new ArrayList<>()),
            new ArrayList<>());
        limitRepository.save(limit1);
    }
    
}