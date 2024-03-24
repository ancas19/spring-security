package com.spring.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.spring.security.repositories",
        entityManagerFactoryRef = "secutiryEntityManager"
)
public class PostgresConection {
    @Value("${spring.jpa.show-sql}")
    private String showSql;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddlAuto;
    @Value("${postgres.dialect}")
    private String dialect;
    @Value("${postgres.driverClassName}")
    private String className;
    @Value("${postgres.url}")
    private String jdbcUrl;
    @Value("${postgres.user}")
    private String jdbcUser;
    @Value("${postgres.pass}")
    private String jdbcPass;
    @Bean
    public DataSource dataSource()  {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPass);
        return dataSource;
    }


    @Bean(name = "secutiryEntityManager")
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.spring.security.entities");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql",showSql);
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        em.setJpaProperties(properties);
        return em;
    }

}
