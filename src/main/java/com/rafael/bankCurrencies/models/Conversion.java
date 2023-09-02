package com.rafael.bankCurrencies.models;

import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class Conversion {
    @PrimaryKey
    private UUID id;
    
    private BigDecimal rate;
    
    private BigDecimal rateOnPreviousClose;
    
    @Column(unique=true)
    private LocalDateTime madeAt;
    
    @Column(unique=true)
    private String symbol;
    
}
