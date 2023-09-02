package com.rafael.bankCurrencies.repository.postgres;

import com.rafael.bankCurrencies.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
    
}