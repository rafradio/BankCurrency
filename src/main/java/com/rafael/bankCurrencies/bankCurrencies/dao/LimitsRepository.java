package com.rafael.bankCurrencies.bankCurrencies.dao;

import com.rafael.bankCurrencies.bankCurrencies.models.Limits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitsRepository extends CrudRepository<Limits, Long>{
    
}
