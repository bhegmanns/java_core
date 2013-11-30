package de.hegmanns.it.schulung.hibernate.domain.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.jdbc.Work;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.hegmanns.it.schulung.dbverbindung.hibernate.HibernateLocator;
import de.hegmanns.it.schulung.dbverbindung.hibernate.SessionTxPair;
import de.hegmanns.it.schulung.hibernate.domain.order.optimisticlocking.OptimisticOrder;
import de.hegmanns.it.schulung.hibernate.domain.order.pessimisticlocking.PessimisticOrder;
import de.hegmanns.test.Component;
import de.hegmanns.test.TestKnow;

@Category(TestKnow.class)
public class ContainedTransactionTest {

	public void txLevel(final SessionTxPair sessionTxPair){

		sessionTxPair.getSession().doWork(new Work(
				) {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			}
		});
		
	}
	
	@Test
	public void testMitSessionTxPair(){
		SessionTxPair tx1 = HibernateLocator.getSessionTxPair();
		SessionTxPair tx2 = HibernateLocator.getSessionTxPair();
		
		// zwei Order aus zwei unterschiedlichen Tx erstellt
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
	
	@Test(expected = GenericJDBCException.class)
	public void zweiHibernateSessionsEineDavonSchnellDazwischen()
	{
		SessionTxPair stp = HibernateLocator.getSessionTxPair();
		
		Order angelegteOrder = new Order();
		angelegteOrder.setDepotnummer("111111");
		angelegteOrder.setZeitpunktErstellung(new Date());
		stp.save(angelegteOrder).commit().close();
		
		SessionTxPair tx1 = HibernateLocator.getSessionTxPair();
		Order durchTx1GeleseneOrder = (Order) tx1.getSession().get(Order.class, angelegteOrder.getId());
		durchTx1GeleseneOrder.setZeitpunktErstellung(DateUtils.addDays(durchTx1GeleseneOrder.getZeitpunktErstellung(), 5));
			SessionTxPair tx2 = HibernateLocator.getSessionTxPair();
			Order durchTx2GeleseneOrder =(Order)tx2.getSession().get(Order.class, angelegteOrder.getId());
			durchTx2GeleseneOrder.setZeitpunktErstellung(DateUtils.addYears(durchTx2GeleseneOrder.getZeitpunktErstellung(), 6));
			tx2.commit().close();
		tx1.commit().close();
	}
	
	@Test
	public void eineSchnelleJdbcTransaktionFunktDazwischen()
	{
		
		SessionTxPair stp = HibernateLocator.getSessionTxPair();
		Order angelegteOrder = new Order();
		angelegteOrder.setDepotnummer("111111");
		angelegteOrder.setZeitpunktErstellung(new Date());
		stp.save(angelegteOrder).commit().close();
		
		SessionTxPair tx1 = HibernateLocator.getSessionTxPair();
		Order durchTx1GeleseneOrder = (Order) tx1.getSession().get(Order.class, angelegteOrder.getId());
		durchTx1GeleseneOrder.setZeitpunktErstellung(DateUtils.addDays(durchTx1GeleseneOrder.getZeitpunktErstellung(), 5));
			SessionTxPair tx2 = HibernateLocator.getSessionTxPair();
			tx2.getSession().doWork(new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
					Statement st = connection.createStatement();
					st.executeUpdate("update orderkern.wp_order o set o.zeitpunkterstellung = o.zeitpunkterstellung + " + (5) + " + " + (20 * 365));
					st.execute("commit");
				}
			});
			tx2.commit().close();
			
		try{
		tx1.commit().close();
		}catch(StaleObjectStateException e)
		{
		}
	}
	
	
	
	
	
	@Test(expected = GenericJDBCException.class)
	public void konkurrierendeSession(){
		
		SessionTxPair tx1 = null;
		SessionTxPair tx2 = null;
		
		// Order erstellen in eigene Session
		tx1 = HibernateLocator.getSessionTxPair();
		Order o1 = new Order();
		o1.setDepotnummer("123456");
		o1.setZeitpunktErstellung(new Date());
		tx1.save(o1).commit();
		
		// Order in andere Transaktion lesen und aendern
		tx2 = HibernateLocator.getSessionTxPair();
		Order o2 = (Order)tx2.getSession().get(Order.class, o1.getId());
		o2.setZeitpunktErstellung(DateUtils.addMinutes(o2.getZeitpunktErstellung(), 20));
		
		// Order in alter Transaktion aendern
		o1.setZeitpunktErstellung(DateUtils.addYears(o1.getZeitpunktErstellung(), 5));
		tx1.commit(); // tx1 committen
		
		tx2.commit(); // tja ...
		
		
		// Hier kommt der gar nicht mehr hin ...
		SessionTxPair tx3 = HibernateLocator.getSessionTxPair();
		Order o3 = (Order)tx3.getSession().get(Order.class, o1.getId());
		System.out.println("" + o3);
	}
	
	
}
