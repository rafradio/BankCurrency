package com.cassandra.test.repository.postgres;

import com.cassandra.test.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
    
}
