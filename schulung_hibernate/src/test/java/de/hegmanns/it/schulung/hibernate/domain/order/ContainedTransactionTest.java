package de.hegmanns.it.schulung.hibernate.domain.order;

import java.util.Date;

import org.junit.Test;

import de.hegmanns.it.schulung.dbverbindung.hibernate.HibernateLocator;
import de.hegmanns.it.schulung.dbverbindung.hibernate.SessionTxPair;
import de.hegmanns.it.schulung.hibernate.domain.order.optimisticlocking.OptimisticOrder;
import de.hegmanns.it.schulung.hibernate.domain.order.pessimisticlocking.PessimisticOrder;

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
	
	
}
