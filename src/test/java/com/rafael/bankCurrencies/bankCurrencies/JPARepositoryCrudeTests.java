package com.rafael.bankCurrencies.bankCurrencies;

import java.sql.SQLException;

public abstract class JPARepositoryCrudeTests extends JPARepositoryTCIntegrationTest {

    public abstract void saveRecords();
    
    public abstract void deleteRecord();
}
