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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "filesEntityManagerFactory", transactionManagerRef = "filesTransactionManager", basePackages = {
        "com.nish.project.repository.files" })
public class FilesDBConfig {
    @Primary
    @Bean(name = "filesDataSource")
    @ConfigurationProperties(prefix = "spring.files.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name = "filesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean filesEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                               @Qualifier("filesDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.nish.project.model.files").persistenceUnit("Files").build();
    }
    @Primary
    @Bean(name = "filesTransactionManager")
    public PlatformTransactionManager filesTransactionManager(
            @Qualifier("filesEntityManagerFactory") EntityManagerFactory filesEntityManagerFactory) {
        return new JpaTransactionManager(filesEntityManagerFactory);
    }
}
