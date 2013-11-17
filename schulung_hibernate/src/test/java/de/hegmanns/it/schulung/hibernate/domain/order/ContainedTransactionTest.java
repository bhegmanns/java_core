package de.hegmanns.it.schulung.hibernate.domain.order;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.hegmanns.it.schulung.dbverbindung.hibernate.HibernateLocator;
import de.hegmanns.it.schulung.dbverbindung.hibernate.SessionTxPair;
import de.hegmanns.it.schulung.hibernate.domain.order.optimisticlocking.OptimisticOrder;
import de.hegmanns.it.schulung.hibernate.domain.order.pessimisticlocking.PessimisticOrder;
import de.hegmanns.test.Component;

@Category(Component.class)
public class ContainedTransactionTest {

	@Test
	public void testMitSessionTxPair(){
		SessionTxPair tx1 = HibernateLocator.getSessionTxPair();
		SessionTxPair tx2 = HibernateLocator.getSessionTxPair();
		
		
		Order order1 = new Order();
		order1.setDepotnummer("123456");
		order1.setZeitpunktErstellung(new Date());
		tx1.save(order1).commit();
		
		
		
		Order order2 = new Order();
		order2.setDepotnummer("123456");
		order2.setZeitpunktErstellung(new Date());
		tx2.save(order2).commit();
		
		PessimisticOrder pessimisticOrder1 = new PessimisticOrder();
		pessimisticOrder1.setDepotnummer("123456");
		pessimisticOrder1.setZeitpunktErstellung(new Date());
		tx1.save(pessimisticOrder1).commit();
		
		
		OptimisticOrder optimisticOrder = new OptimisticOrder();
		optimisticOrder.setDepotnummer("123456");
		optimisticOrder.setZeitpunktErstellung(new Date());
		tx1.getSession().save(optimisticOrder);
		tx1.commit();
		
	}
	
	@Test
	public void konkurrierendeSession(){
		
		SessionTxPair tx1 = null;
		SessionTxPair tx2 = null;
		
		tx1 = HibernateLocator.getSessionTxPair();
		tx1.getSession().beginTransaction();
		Order o1 = new Order();
		o1.setDepotnummer("123456");
		o1.setZeitpunktErstellung(new Date());
		tx1.save(o1).commit();
		
		tx2 = HibernateLocator.getSessionTxPair();
		Order o2 = (Order)tx2.getSession().get(Order.class, o1.getId());
		o2.setZeitpunktErstellung(DateUtils.addMinutes(o2.getZeitpunktErstellung(), 20));
		
		o1.setZeitpunktErstellung(DateUtils.addYears(o1.getZeitpunktErstellung(), 5));
		tx1.commit();
		tx2.commit();
		
		SessionTxPair tx3 = HibernateLocator.getSessionTxPair();
		Order o3 = (Order)tx3.getSession().get(Order.class, o1.getId());
		System.out.println("" + o3);
	}
	
	
}
