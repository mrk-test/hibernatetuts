package com.mnazareno.hibernatetuts.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure();
			ServiceRegistry registry = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			return cfg.buildSessionFactory(registry);
		} catch (HibernateException e) {
			System.err.println("Error initializing session factory.");
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
