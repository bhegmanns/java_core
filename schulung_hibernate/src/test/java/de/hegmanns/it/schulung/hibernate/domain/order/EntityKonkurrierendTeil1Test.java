package de.hegmanns.it.schulung.hibernate.domain.order;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.hegmanns.it.schulung.hibernate.domain.order.optimisticlocking.OptimisticOrder;
import de.hegmanns.it.schulung.hibernate.domain.order.pessimisticlocking.PessimisticOrder;
import de.hegmanns.test.Component;

@Category(Component.class)
public class EntityKonkurrierendTeil1Test {
	
private static SessionFactory sessionFactory = null;
	
	@BeforeClass
	public static void initialisiere(){
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

	@Test
	public void orderAnlegenUndAendern(){
		try{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Order order = new Order();
		order.setDepotnummer("12345678");
		order.setZeitpunktErstellung(new Date());
		session.save(order);
		
		long id = order.getId();
		System.out.println("Id = " + id);
		tx.commit();
		
		tx = session.getTransaction();
		tx.setTimeout(0);
		tx.begin();
		Order geleseneOrder = (Order) session.load(Order.class, id);
		geleseneOrder.setZeitpunktErstellung(DateUtils.addDays(geleseneOrder.getZeitpunktErstellung(), 20));
		session.saveOrUpdate(order);;
		tx.commit();
		session.close();
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
}
