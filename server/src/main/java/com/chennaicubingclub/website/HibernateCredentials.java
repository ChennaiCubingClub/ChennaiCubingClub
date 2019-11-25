package com.chennaicubingclub.website;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HibernateCredentials {
 
	static Properties properties;
    
    @Autowired
    public HibernateCredentials(
    		@Value("${hibernate.bytecode.use_reflection_optimizer}") String use_reflection_optimizer,
    		@Value("${hibernate.connection.driver_class}") String driver_class,
    		@Value("${hibernate.dialect}") String dialect,
    		@Value("${hibernate.connection.url}") String url,
    		@Value("${hibernate.connection.username}") String username,
    		@Value("${hibernate.connection.password}") String password,
    		@Value("${show_sql}") String show_sql
    		) {
        properties = new Properties();
        properties.put("hibernate.bytecode.use_reflection_optimizer", use_reflection_optimizer);
		properties.put("hibernate.connection.driver_class", driver_class);
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.connection.url", url);
		properties.put("hibernate.connection.username", username);
		properties.put("hibernate.connection.password", password);
		properties.put("show_sql", show_sql);
    }
}