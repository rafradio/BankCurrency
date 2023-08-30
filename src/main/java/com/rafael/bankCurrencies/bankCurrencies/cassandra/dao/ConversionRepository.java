package com.rafael.bankCurrencies.bankCurrencies.cassandra.dao;

import com.rafael.bankCurrencies.bankCurrencies.models.Conversion;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ConversionRepository extends CassandraRepository<Conversion, UUID> {
    
}
