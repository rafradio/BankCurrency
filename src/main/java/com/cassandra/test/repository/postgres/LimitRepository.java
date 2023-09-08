package com.cassandra.test.repository.postgres;

import com.cassandra.test.models.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends CrudRepository<Limit, Long>{
    
}
