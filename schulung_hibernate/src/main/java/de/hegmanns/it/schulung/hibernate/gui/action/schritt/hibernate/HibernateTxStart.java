package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateTxStart extends Schritt{
	
	public static final String HIBERNATE_TRANSACTION = "HIBERNATE_TRANSACTION";
	
	public HibernateTxStart()
	{
		super(new String[]{HIBERNATE_TRANSACTION}, new String[]{HibernateInitSession.HIBERNATE_SESSION}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		Transaction tx = cache.getFromCache(HibernateInitSession.HIBERNATE_SESSION, Session.class).beginTransaction();
		cache.setIntoCache(HIBERNATE_TRANSACTION, tx);
	}

}
