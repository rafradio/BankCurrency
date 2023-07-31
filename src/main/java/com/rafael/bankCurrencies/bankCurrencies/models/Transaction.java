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
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="transaction")
@Getter @Setter @NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;
    
    private LocalDateTime created;
    
    @Column(length=10)
    private String accountFrom;
    
    @Column(length=10)
    private String accountTo;
    
    private BigDecimal sum;
    
    private String currencyShortname;
    
    private String expenseCategory;
    
    private boolean exceeded;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name="limit_id")
    private Limit limit;

    public Transaction(Long id, LocalDateTime created, String accountFrom, String accountTo, BigDecimal sum, String currencyShortname, String expenseCategory, boolean exceeded, Client client, Limit limit) {
        this.id = id;
        this.created = created;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
        this.currencyShortname = currencyShortname;
        this.expenseCategory = expenseCategory;
        this.exceeded = exceeded;
        this.client = client;
        this.limit = limit;
    }

    
}
