package com.cassandra.test.repository.cassandra;

import com.cassandra.test.models.Conversion;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRepository extends CassandraRepository<Conversion, UUID> {
    
}
