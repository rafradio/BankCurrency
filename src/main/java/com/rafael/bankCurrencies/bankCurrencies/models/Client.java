package com.rafael.bankCurrencies.bankCurrencies.models;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="client")
@Getter @Setter @NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private long id;
    
    private LocalDateTime created;
    
    @Column(unique=true, length=10)
    private String bankAccountNumber;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Transaction> transactions;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Limit> limits;
    

    public Client(Long id, LocalDateTime created, String bankAccountNumber, List<Transaction> transactions, List<Limit> limits) {
        this.id = id;
        this.created = created;
        this.bankAccountNumber = bankAccountNumber;
        this.transactions = transactions;
        this.limits = limits;
    }

}
