package com.tomniu.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Database configurations.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	/**
	 * Declare the JPA entity manager factory.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory =
		    new LocalContainerEntityManagerFactoryBean();
	
		entityManagerFactory.setDataSource(dataSource);
	
		// Classpath scanning of @Component, @Service, etc annotated class
		entityManagerFactory.setPackagesToScan(
		    env.getProperty("entitymanager.packagesToScan"));
	
		// Vendor adapter
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
	
		// Hibernate properties
		Properties additionalProperties = new Properties();
		additionalProperties.put(
		    "hibernate.dialect", 
		    env.getProperty("hibernate.dialect"));
		additionalProperties.put(
		    "hibernate.show_sql", 
		    env.getProperty("hibernate.show_sql"));
		additionalProperties.put(
		    "hibernate.hbm2ddl.auto", 
		    env.getProperty("hibernate.hbm2ddl.auto"));
		entityManagerFactory.setJpaProperties(additionalProperties);
	
		return entityManagerFactory;
	}

	/**
	 * Declare the transaction manager.
	*/
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = 
			new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(
	        entityManagerFactory.getObject());
	    return transactionManager;
	}
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }
	// ------------------------
	// PRIVATE
	// ------------------------
  
	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
}