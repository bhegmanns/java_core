package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import org.hibernate.Transaction;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateTxEnd extends Schritt {
	
	public static final String TX_COMMIT_JN = "TX_COMMIT_JN";
	private boolean doCommit = true;
	
	public HibernateTxEnd(){
		super(new String[]{}, new String[]{HibernateTxStart.HIBERNATE_TRANSACTION, TX_COMMIT_JN}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		Transaction tx = cache.getFromCache(HibernateTxStart.HIBERNATE_TRANSACTION, Transaction.class);
		
		String indikator = cache.getFromCache(TX_COMMIT_JN, String.class);
		if (indikator != null && indikator.toLowerCase().startsWith("n"))
		{
			doCommit = false;
		}
		
		if (doCommit)
		{
			System.out.println("Transaktion wird commit");
			tx.commit();
			System.out.println("committed");
		}
		else
		{
			System.out.println("Transaktion wird rollback");
			tx.rollback();
			System.out.println("rollbacked");
		}

	}

}
