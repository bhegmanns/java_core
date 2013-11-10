package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateInitSession extends Schritt {
	
	public static final String HIBERNATE_SESSION = "HIBERNATE_SESSION";
	
	public HibernateInitSession(){
		super(new String[]{HIBERNATE_SESSION}, new String[]{HibernateInitSessionFactory.HIBERNATE_SESSION_FACTORY}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		Session hibernateSession = cache.getFromCache(HibernateInitSessionFactory.HIBERNATE_SESSION_FACTORY, SessionFactory.class).openSession();
		cache.setIntoCache(HIBERNATE_SESSION, hibernateSession);
	}

}
