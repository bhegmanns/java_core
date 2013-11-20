package de.hegmanns.it.schulung.hibernate.domain.order;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.junit.Test;

import de.hegmanns.it.schulung.dbverbindung.hibernate.HibernateLocator;
import de.hegmanns.it.schulung.dbverbindung.hibernate.SessionTxPair;
import de.hegmanns.it.schulung.hibernate.domain.order.pessimisticlocking.PessimisticOrder;

public class PessimisticLockingTest {

	@Test
	public void zweiTransaktionen(){
		SessionTxPair tx1 = null;
		SessionTxPair tx2 = null;
		
		tx1 = HibernateLocator.getSessionTxPair();
		PessimisticOrder po1 = new PessimisticOrder();
		po1.setDepotnummer("123456");
		po1.setZeitpunktErstellung(new Date());
		tx1.save(po1).commit();
		po1.setZeitpunktErstellung(DateUtils.setHours(po1.getZeitpunktErstellung(), 5));
		tx2 = HibernateLocator.getSessionTxPair();
		PessimisticOrder po2 = (PessimisticOrder) tx2.getSession().get(PessimisticOrder.class, po1.getId());
		po2.setZeitpunktErstellung(DateUtils.addDays(po2.getZeitpunktErstellung(), 3));
		
		tx1.getSession().getTransaction().commit();
		tx2.getSession().getTransaction().commit();
		
		SessionTxPair tx3 = HibernateLocator.getSessionTxPair();
		PessimisticOrder po3 = (PessimisticOrder)tx3.getSession().get(PessimisticOrder.class, po2.getId());
		System.out.println("po1 = " + po1.getZeitpunktErstellung());
		System.out.println("po2 = " + po2.getZeitpunktErstellung());
		System.out.println("po3 = " + po3.getZeitpunktErstellung());
	}
	
	@Test
	public void zweiTransaktionenMitLock() throws InterruptedException{
		SessionTxPair tx1 = null;
		SessionTxPair tx2 = null;
		
		tx1 = HibernateLocator.getSessionTxPair();
		PessimisticOrder po1 = new PessimisticOrder();
		po1.setDepotnummer("123456");
		po1.setZeitpunktErstellung(new Date());
		tx1.save(po1).commit();
		tx1.getSession().lock(po1, LockMode.FORCE);
		tx1.getSession().getTransaction().setTimeout(1);
		po1.setZeitpunktErstellung(DateUtils.setHours(po1.getZeitpunktErstellung(), 5));
		tx2 = HibernateLocator.getSessionTxPair();
		tx2.getSession().getTransaction().setTimeout(1);
		PessimisticOrder po2 = (PessimisticOrder) tx2.getSession().get(PessimisticOrder.class, po1.getId());
		po2.setZeitpunktErstellung(DateUtils.addDays(po2.getZeitpunktErstellung(), 3));
		
		System.out.println("tx2 soll committet werden.");
		
		TxComitter txComitter = new TxComitter(tx2.getSession().getTransaction());
		Watchdog watchdog = new Watchdog(txComitter, 500);
		watchdog.start();
		watchdog.join();
//		tx2.getSession().getTransaction().commit();
		System.out.println("    tx2 commitet");
		tx1.getSession().getTransaction().commit();
		
		
		SessionTxPair tx3 = HibernateLocator.getSessionTxPair();
		PessimisticOrder po3 = (PessimisticOrder)tx3.getSession().get(PessimisticOrder.class, po2.getId());
		System.out.println("po1 = " + po1.getZeitpunktErstellung());
		System.out.println("po2 = " + po2.getZeitpunktErstellung());
		System.out.println("po3 = " + po3.getZeitpunktErstellung());
	}
	
	
}

class TxComitter implements Runnable
{
	private static final Logger LOG = Logger.getLogger(TxComitter.class);
	private boolean hasComitted = false;
	private org.hibernate.Transaction tx;
	
	public TxComitter(org.hibernate.Transaction tx)
	{
		this.tx = tx;
	}

	@Override
	public void run() {
//		tx.
		tx.commit();
		hasComitted = true;
	}
	
	public boolean hasCommited()
	{
		return hasComitted;
	}
	
	public void cleanup()
	{
		LOG.warn("Transaktion " + tx.toString() + " ROLLBACK!!!");
		System.out.println("Transaktion " + tx.toString() + " ROLLBACK!!!");
		tx.rollback();
		System.out.println("Transaktion zurueck gerollt!!!");
	}
	
}

class Watchdog extends Thread
{
	private TxComitter runnable;
	private long timeout;
	
	public Watchdog(TxComitter runnable, long timeout)
	{
		this.runnable = runnable;
		this.timeout = timeout;
	}
	
	@Override
	public void run(){
		
		Thread thread = new Thread(runnable);
		thread.start();
		long systemStart = System.currentTimeMillis();
		
		while (true)
		{
			if (!thread.isAlive()){
				System.out.println("commit gesetzt");
				break;
			}
			else
			{
				System.out.println(">kein commit gesetzt");
			}
			
			try {
				System.out.print("Ich warte ...");
				Thread.sleep((timeout / 100) + 1);
				System.out.println(" FERTIG");
			} catch (InterruptedException e) {
				System.out.println(" ABGEBROCHEN");
			}
			
			if (System.currentTimeMillis() > (systemStart + timeout))
			{
				System.out.println("Ausstieg nach " + timeout + "ms");
				break;
			}
		}
		
		if (thread.isAlive())
		{
			System.out.println("Thread wird abgebrochen!!!");
			
			thread.suspend();
//			thread.destroy();
			runnable.cleanup();
			thread = null;
		}
		else
		{
			System.out.println("Thread ist comittet!!!");
		}
	}
}
