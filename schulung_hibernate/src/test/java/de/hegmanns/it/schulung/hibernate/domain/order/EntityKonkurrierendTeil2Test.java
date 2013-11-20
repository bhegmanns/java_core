package de.hegmanns.it.schulung.hibernate.domain.order;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;

//@Category(Component.class)
public class EntityKonkurrierendTeil2Test {

private static SessionFactory sessionFactory = null;
	
	@BeforeClass
	public static void initialisiere(){
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(Orderposten.class);
		configuration.addAnnotatedClass(Orderteil.class);
		configuration.addAnnotatedClass(Ordervorgang.class);
		
		
		configuration.configure();
		
		sessionFactory = configuration.buildSessionFactory();
	}
	
	private long id = 267850;
	@Test
	public void orderAendern(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Order order = (Order)session.get(Order.class, id);
		System.out.println("Order mit id=" + order.getId() + " gelesen");
		
		order.setZeitpunktErstellung(DateUtils.addYears(order.getZeitpunktErstellung(), 5));
		
		tx.commit();
		session.close();
	}
}
