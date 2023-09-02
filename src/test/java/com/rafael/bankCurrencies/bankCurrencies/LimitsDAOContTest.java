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

public class LimitsDAOContTest extends JPARepositoryTCIntegrationTest {
    @Autowired
    private LimitRepository limitRepository;
    
    @Test
    public void whenInsertToRepository_limitCheckID_thenTrue() {
        Limit limit = this.insertToRepository_Limit();
        Optional<Limit> limitResponse =  limitRepository.findById(limit.getId());
        limitResponse.ifPresent(lmt -> {
            assertEquals("First test on Limit", limit.getId(), lmt.getId());
        });
        
    }
    
    @Test
    public void whenDeleted_limitEntity_thenTrue() {
    }
    
    public Limit insertToRepository_Limit() {
        Client client = Client.builder()
                .bankAccountNumber("qwertweo57")
                .transactions(new ArrayList<>())
                .allLimits(new ArrayList<>())
                .build();
        Limit limit1 = new Limit(new BigDecimal("135.69"), 
            new BigDecimal("135.69"), "USD", "USD", 
            client,
            new ArrayList<>());
        return limitRepository.save(limit1);
    }
    
}