package com.rafael.bankCurrencies.bankCurrencies.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime created;
    
    @Column(length=10)
    private String accountFrom;
    
    @Column(length=10)
    private String accountTo;
    
    private BigDecimal sum;
    
    private String currencyShortname;
    
    private String expenseCategory;
    
    private Boolean exceeded;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name="limit_id")
    private Limit limit;
    
}