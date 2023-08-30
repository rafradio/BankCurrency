package com.rafael.bankCurrencies.bankCurrencies.config;

//import com.rafael.bankCurrencies.bankCurrencies.dao.ClientRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = {"com.rafael.bankCurrencies.bankCurrencies.dao"},
  entityManagerFactoryRef = "postgresEntityManagerFactory",
  transactionManagerRef = "postgresTransactionManager"
)
public class PostgresDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean
    public DataSource postgresDataSource() {
        return postgresDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            @Qualifier("postgresDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.rafael.bankCurrencies.bankCurrencies.dao")
                .build();
    }
    
    @Bean
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactory") LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(postgresEntityManagerFactory.getObject()));
    }
    
}
