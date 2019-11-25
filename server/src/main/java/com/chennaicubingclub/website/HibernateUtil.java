package com.chennaicubingclub.website;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import com.chennaicubingclub.website.data.C3CompetitionsTable;
import com.chennaicubingclub.website.data.C3CupPointsTable;
import com.chennaicubingclub.website.data.C3UsersTable;
import com.chennaicubingclub.website.data.CompetitionsTable;
import com.chennaicubingclub.website.data.PersonsTable;
import com.chennaicubingclub.website.data.ResultsTable;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory = buildSessionFactory();
    
	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
	    configuration.configure();
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
//		Configuration configuration = new Configuration();
//		configuration.setProperties(HibernateCredentials.properties);
//		configuration = configuration.addAnnotatedClass(C3CupPointsTable.class);
//		configuration = configuration.addAnnotatedClass(C3UsersTable.class);
//		configuration = configuration.addAnnotatedClass(C3CompetitionsTable.class);
//		configuration = configuration.addAnnotatedClass(CompetitionsTable.class);
//		configuration = configuration.addAnnotatedClass(ResultsTable.class);
//		configuration = configuration.addAnnotatedClass(PersonsTable.class);
//	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
//	            configuration.getProperties()).build();
//	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//	    return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }
}
