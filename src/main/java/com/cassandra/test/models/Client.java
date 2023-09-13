package com.cassandra.test.models;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter 
@Setter 
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @CreationTimestamp
    private LocalDateTime created;
    
    @Column(unique=true)
    @Pattern(regexp = "\\d{10}", message = "bankAccountNumber must be 10 length only digits")
    private String bankAccountNumber;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Transaction> transactions;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Limit> allLimits;
    
}
