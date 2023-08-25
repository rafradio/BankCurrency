package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import com.rafael.bankCurrencies.bankCurrencies.models.Limits;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.rafael.bankCurrencies.bankCurrencies.dao.LimitsRepository;
import java.sql.SQLException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

public class LimitsDAOContTest extends JPARepositoryCrudeTests {
    @Autowired
    private LimitsRepository limitsRepository;
    
    @Test
    @Override
    public void saveRecords() {
        Limits limit1 = new Limits(6L, LocalDateTime.now(), new BigDecimal("135.69"), 
                new BigDecimal("135.69"), "USD", "USD", 
                new Client(1L, LocalDateTime.now(), "qwertweo57", new ArrayList<>(), new ArrayList<>()),
                new ArrayList<>());
        limitsRepository.save(limit1);
        Optional<Limits> limitResponse =  limitsRepository.findById(1L);
        Limits lmt = limitResponse.get();
        assertThat(lmt.getId()).as("First test on Limit").isEqualTo(1L);
        
    }
    
    @Test
    @Override
    public void deleteRecord() {
        
    }
    
    
}