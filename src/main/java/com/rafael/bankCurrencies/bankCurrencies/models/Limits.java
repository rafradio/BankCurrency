package com.rafael.bankCurrencies.bankCurrencies.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class Limits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime created;
    
    private BigDecimal sum;
    
    private BigDecimal remaining;
    
    private String currencyShortname;
    
    private String expenseCategory;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "limits")
    private List<Transaction> transactions;
    
}