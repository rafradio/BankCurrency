package com.cassandra.test.repository.postgres;

import com.cassandra.test.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    
}
