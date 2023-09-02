package com.rafael.bankCurrencies.repository.postgres;

import com.rafael.bankCurrencies.models.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends CrudRepository<Limit, Long>{
    
}