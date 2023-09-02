package com.rafael.bankCurrencies.bankCurrencies.dao;

import com.rafael.bankCurrencies.bankCurrencies.models.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends CrudRepository<Limit, Long>{
    
}