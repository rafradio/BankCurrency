package com.cassandra.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class TestContext {
    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean("testContext")
    public ApplicationContext getContext() {
        return this.applicationContext;
    }
}
