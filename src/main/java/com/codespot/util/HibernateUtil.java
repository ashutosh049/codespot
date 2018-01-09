package com.codespot.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	/**
	 * @deprecated However, since Hibernate 4.x, this approach is deprecated.
	 *             According to Hibernate 4.0 API docs, the Configuration class’
	 *             buildSessionFactory() method is deprecated and it recommends
	 *             developers to use the buildSessionFactory(ServiceRegistry)
	 *             instead
	 */
	@SuppressWarnings("deprecation")
	public static Session getHibernateSession() {
		final SessionFactory sf = new Configuration().configure().buildSessionFactory();
		final Session session = sf.openSession();
		return session;
	}

	/**
	 * @deprecated The Configuration class’ configure() method loads mappings
	 *             and properties from the convention resource file
	 *             hibernate.cfg.xml which should be present in the classpath.
	 *             It’s also very common to create a separate utility class for
	 *             building the SessionFactory as follow As of 2014, the above
	 *             code is deprecated
	 */
	public static SessionFactory getSessionFactoryDeprecated() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure();
			ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
			registry.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			// loads configuration and mappings
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			// builds a session factory from the service registry
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}
	
	/** Usecase--------- **/
/*	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	session.save(myObject);*/

}