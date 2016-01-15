package com.example.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.apache.derby.jdbc.ClientDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.repository.project", entityManagerFactoryRef = "projectEntityManager", transactionManagerRef = "projectTransactionManager")
@EnableTransactionManagement
public class ProjectConfig {

	
	
	///http://stackoverflow.com/questions/26308035/spring-boot-spring-data-jpa-with-multiple-datasources 참조
	@Value("${projectDatasource.datasource.url}")
	private String databaseUrl;

	@Value("${projectDatasource.datasource.username}")
	private String username;

	@Value("${projectDatasource.datasource.password}")
	private String password;

	@Value("${projectDatasource.datasource.driverClassName}")
	private String driverClassName;

	@Value("${projectDatasource.datasource.hibernate.dialect}")
	private String dialect;

	@Bean(name = "projectDatasource")
	@ConfigurationProperties(prefix="projectDatasource.datasource")
	public DataSource projectDataSource() {
		DataSource ds =  DataSourceBuilder.create().build();
		/*DriverManagerDataSource sdDatasource = new DriverManagerDataSource();
		// sdDatasource.setDriverClass(org.mariadb.jdbc.Driver.class);
		sdDatasource.setDriverClassName(driverClassName);
		sdDatasource.setUsername(username);
		sdDatasource.setPassword(password);

		sdDatasource.setUrl(databaseUrl); // "jdbc:mariadb://localhost:3306/example");
*/
		return ds;
	}

	@Bean(name = "projectEntityManager")
	public EntityManagerFactory projectEntityManagerFactory() throws Throwable {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(projectDataSource());
		entityManager.setJpaVendorAdapter(vendorAdapter);
		entityManager.setPackagesToScan("com.example.dto.projectInfo");

		entityManager.setPersistenceUnitName("projectPersistenceUnit");
		Properties properties = new Properties();

		properties.setProperty("hibernate.dialect", dialect);
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

	@Bean(name = "projectTransactionManager")
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager jpamanager = new JpaTransactionManager();
		try {
			jpamanager.setEntityManagerFactory(projectEntityManagerFactory());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jpamanager;
	}

	@Bean(name = "projectEntityManager")
	public EntityManager projectEntityManager() {
		try {
			return projectEntityManagerFactory().createEntityManager();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
