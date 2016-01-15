package com.example.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.apache.derby.jdbc.ClientDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.repository.user", entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "userTransactionManager")
@EnableTransactionManagement
public class UserConfig {

	/*
	 * @Bean(name = "userDatasource") public DataSource userDataSource() {
	 * SimpleDriverDataSource sdDatasource = new SimpleDriverDataSource();
	 * sdDatasource.setDriverClass(ClientDriver.class);
	 * sdDatasource.setUsername("user"); sdDatasource.setPassword("1234");
	 * 
	 * sdDatasource.setUrl("jdbc:derby://localhost:1527/example;create=true");
	 * 
	 * return sdDatasource; }
	 */

	@Bean(name = "userDatasource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource userDataSource() {
		DataSource ds = DataSourceBuilder.create().build();
		return ds;
	}

	@Bean(name = "userEntityManager")
	public EntityManagerFactory userEntityManagerFactory() throws Throwable {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();

		entityManager.setDataSource(userDataSource());
		entityManager.setJpaVendorAdapter(vendorAdapter);
		entityManager.setPackagesToScan("com.example.dto.userInfo");

		entityManager.setPersistenceUnitName("userPersistenceUnit");
		Properties properties = new Properties();

		properties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.DerbyDialect");
		properties.setProperty("hibernate.connection.pool_size", "4");
		properties.setProperty("hibernate.connection.shutdown", "true");
		properties.setProperty("hibernate.show_sql", "true");

		properties.setProperty("hibernate.default_schema", "example");
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		entityManager.setJpaProperties(properties);

		// / 매우 매우 매우 중요!!!!!!!!!!!!!!!! 안하면 entity factory 생성이 안됨.
		entityManager.afterPropertiesSet();
		// entity manager getObject 를 통해서 싱글톤 엔티티 매니저 인스턴스를 생성해야 함.
		return entityManager.getObject();
	}

	@Bean(name = "userTransactionManager")
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager jpamanager = new JpaTransactionManager();
		try {
			jpamanager.setEntityManagerFactory(userEntityManagerFactory());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jpamanager;
	}

	@Bean(name = "userEntityManager")
	public EntityManager userEntityManager() {
		try {
			return userEntityManagerFactory().createEntityManager();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
