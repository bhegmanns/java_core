package de.hegmanns.it.schulung.dbverbindung.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import de.hegmanns.it.schulung.hibernate.domain.order.Order;
import de.hegmanns.it.schulung.hibernate.domain.order.Orderposten;
import de.hegmanns.it.schulung.hibernate.domain.order.Orderteil;
import de.hegmanns.it.schulung.hibernate.domain.order.Ordervorgang;
import de.hegmanns.it.schulung.hibernate.domain.order.optimisticlocking.OptimisticOrder;
import de.hegmanns.it.schulung.hibernate.domain.order.pessimisticlocking.PessimisticOrder;

public class HibernateLocator {

	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory(){
		if (sessionFactory == null)
		{
			init();
		}
		
		return sessionFactory;
	}
	
	public static SessionTxPair getSessionTxPair(){
		return new SessionTxPair(openSession());
	}
	
	public static Session openSession(){
		return getSessionFactory().openSession();
	}
	
	private static void init(){
		
		synchronized (HibernateLocator.class) {
			if (sessionFactory != null)
			{
				return;
			}
			AnnotationConfiguration configuration = new AnnotationConfiguration();
			configuration.addAnnotatedClass(Order.class);
			configuration.addAnnotatedClass(Orderposten.class);
			configuration.addAnnotatedClass(Orderteil.class);
			configuration.addAnnotatedClass(Ordervorgang.class);
			
			configuration.addAnnotatedClass(OptimisticOrder.class);
			configuration.addAnnotatedClass(PessimisticOrder.class);
			
			
			configuration.configure();
			
			sessionFactory = configuration.buildSessionFactory();
		}
		
	}
}
