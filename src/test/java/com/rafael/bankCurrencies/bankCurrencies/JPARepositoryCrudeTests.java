package com.rafael.bankCurrencies.bankCurrencies;

import java.sql.SQLException;

public abstract class JPARepositoryCrudeTests extends JPARepositoryTCIntegrationTest {
    public abstract void ifTableExist() throws SQLException;
;
    
    public abstract void saveRecords();
}
