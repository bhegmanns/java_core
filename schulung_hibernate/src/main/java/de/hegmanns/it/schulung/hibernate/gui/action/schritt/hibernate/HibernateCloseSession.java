package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import org.hibernate.Session;

import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateCloseSession extends Schritt {

	
	public HibernateCloseSession(){
		super(new String[]{}, new String[]{HibernateInitSession.HIBERNATE_SESSION}, new String[]{HibernateInitSession.HIBERNATE_SESSION});
	}
	
	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		cache.getFromCache(HibernateInitSession.HIBERNATE_SESSION, Session.class).close();
		cache.removeFromCache(HibernateInitSession.HIBERNATE_SESSION);
	}

}
