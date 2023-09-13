package com.cassandra.test.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Table(name = "bank_currencies.conversion")
@Entity
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class Conversion {   
    @Id
    @PrimaryKey
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    
    private BigDecimal rate;
    
//    @Column(name="rate_on_previous_close")
    private BigDecimal rateOnPreviousClose;
    
    @Column(unique=true)
//    @CreationTimestamp
    private LocalDateTime madeAt;
    
    @Column(unique=true)
    private String symbol;
    
}
