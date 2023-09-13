package com.cassandra.test.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="limits")
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @CreationTimestamp
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

    public Limit(BigDecimal sum, BigDecimal remaining, String currencyShortname,
            String expenseCategory, Client client, List<Transaction> transactions) {
        this.sum = sum;
        this.remaining = remaining;
        this.currencyShortname = currencyShortname;
        this.expenseCategory = expenseCategory;
        this.client = client;
        this.transactions = transactions;
    }
      
}