package com.nish.project.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "customerFilesEntityManagerFactory", transactionManagerRef = "customerFilesTransactionManager", basePackages = {
        "com.nish.project.repository.customerfiles" })
public class CustomerFilesDBConfig {
    @Bean(name = "customerFilesDataSource")
    @ConfigurationProperties(prefix = "spring.customerfiles.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "customerFilesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customerFilesEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("customerFilesDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.nish.project.model.customerFiles").persistenceUnit("Customerfiles").build();
    }

    @Bean(name = "customerFilesTransactionManager")
    public PlatformTransactionManager customerFilesTransactionManager(
            @Qualifier("customerFilesEntityManagerFactory") EntityManagerFactory customerFilesEntityManagerFactory) {
        return new JpaTransactionManager(customerFilesEntityManagerFactory);
    }
}

