package com.rafael.bankCurrencies.bankCurrencies;

import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Instant;

@SpringBootTest
class BankCurrenciesApplicationTests {
    
    Client c = new Client(3L, Instant.now(), "qwertweoqi", new ArrayList<>(), new ArrayList<>());

    @Test
    public void contextLoads() {
//        session = sessionFactory.openSession();
        Long b = Long.valueOf(3L);
        System.out.println(c.getCreated().getClass().getSimpleName()); 
        assertEquals("Creating obj with allargsconstr ", b, c.getId()); 
    }

    @Test
    public void fieldTest(){
//        boolean b = c.getCreated() instanceof String;
        System.out.println(c.getCreated()); 
        assertTrue(c.getCreated() instanceof Instant);
    }
}
