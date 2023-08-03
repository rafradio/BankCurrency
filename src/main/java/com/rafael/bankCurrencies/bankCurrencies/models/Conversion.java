package com.rafael.bankCurrencies.bankCurrencies.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="conversion")
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    
    private BigDecimal rate;
    
    private BigDecimal rateOnPreviousClose;
    
    @Column(unique=true)
    private LocalDateTime madeAt;
    
    @Column(unique=true)
    private String symbol;
 
}