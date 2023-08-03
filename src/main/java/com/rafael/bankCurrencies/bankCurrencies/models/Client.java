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
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @CreationTimestamp
    private LocalDateTime created;
    
    @Column(unique=true, length=10)
    private String bankAccountNumber;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Transaction> transactions;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Limit> limits;

}