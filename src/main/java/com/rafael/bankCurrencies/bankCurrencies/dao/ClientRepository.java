package com.rafael.bankCurrencies.bankCurrencies.dao;

import com.rafael.bankCurrencies.bankCurrencies.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClientRepository extends CrudRepository<Client, Integer>{
    
}
