package com.cassandra.test.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @CreationTimestamp
    private LocalDateTime created;
    
    @Pattern(regexp = "\\d{10}", message = "bankAccountNumber must be 10 length only digits")
    private String accountFrom;
    
    @Pattern(regexp = "\\d{10}", message = "bankAccountNumber must be 10 length only digits")
    private String accountTo;
    
    private BigDecimal sum;
    
    private String currencyShortname;
    
    private String expenseCategory;
    
    private Boolean exceeded;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name="limits_id")
    private Limit limits;

    public Transaction(String accountFrom, String accountTo, BigDecimal sum, 
            String currencyShortname, String expenseCategory, Boolean exceeded, Client client, Limit limits) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
        this.currencyShortname = currencyShortname;
        this.expenseCategory = expenseCategory;
        this.exceeded = exceeded;
        this.client = client;
        this.limits = limits;
    }
   
}
