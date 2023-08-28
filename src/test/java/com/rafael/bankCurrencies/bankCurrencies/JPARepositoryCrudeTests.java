package com.rafael.bankCurrencies.bankCurrencies;

import java.sql.SQLException;

public abstract class JPARepositoryCrudeTests extends JPARepositoryTCIntegrationTest {

    public abstract void insertObjectToRepository();
    
    public abstract void deleteObjectFromRepository();
}
