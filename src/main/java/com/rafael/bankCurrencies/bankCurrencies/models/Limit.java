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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="limit")
@Getter @Setter @NoArgsConstructor
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "limit_id")
    private Long id;
    
    private LocalDateTime created;
    
    private BigDecimal sum;
    
    private BigDecimal remaining;
    
    private String currencyShortname;
    
    private String expenseCategory;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "limit")
    private List<Transaction> transactions;
    

    public Limit(Long id, LocalDateTime created, BigDecimal sum, BigDecimal remaining, String currencyShortname, String expenseCategory, Client client, List<Transaction> transactions) {
        this.id = id;
        this.created = created;
        this.sum = sum;
        this.remaining = remaining;
        this.currencyShortname = currencyShortname;
        this.expenseCategory = expenseCategory;
        this.client = client;
        this.transactions = transactions;
    }

}
