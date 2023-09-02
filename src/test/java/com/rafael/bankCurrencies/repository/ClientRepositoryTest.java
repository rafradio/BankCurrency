package com.rafael.bankCurrencies.repository;

import com.rafael.bankCurrencies.AbstractTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext
@RequiredArgsConstructor
public class ClientRepositoryTest extends AbstractTest {

    /**
     * Можно использовать @PostConstruct, дабы не вызывать сохранение перед каждым тестом
     * но в таком случае может криво работать @DirtiesContext
     */
    @BeforeEach
    public void saveTestData() {
        clientRepository.save(CLIENT_1);
    }

    @Test
    public void whenInsertToRepository_getClientById_thenEquals() {
        clientRepository.findById(CLIENT_1.getId())
                .ifPresentOrElse(client -> {
                    assertEquals(CLIENT_1.getBankAccountNumber(), client.getBankAccountNumber());
                    Duration duration = Duration.between(client.getCreated(), LocalDateTime.now());
                    long diff = Math.abs(duration.toSeconds());
                    assertTrue(diff <= 10);
                }, () -> {
                    throw new IllegalStateException("The user wasn't found");
                });
    }
}