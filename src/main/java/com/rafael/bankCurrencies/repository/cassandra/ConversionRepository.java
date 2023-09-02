package com.rafael.bankCurrencies.repository.cassandra;

import com.rafael.bankCurrencies.models.Conversion;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ConversionRepository extends CassandraRepository<Conversion, UUID> {
    
}