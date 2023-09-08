package com.cassandra.test.models;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import jakarta.persistence.Column;

@Table(name = "bank_currencies.conversion")
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
