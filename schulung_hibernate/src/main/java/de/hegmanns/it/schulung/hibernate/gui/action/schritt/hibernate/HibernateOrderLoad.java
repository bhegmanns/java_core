package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import org.hibernate.Session;

import de.hegmanns.it.schulung.hibernate.domain.order.Order;
import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateOrderLoad extends Schritt {
	
	public static final String HIBERNATE_OBJEKT_ID = "HIBERNATE_OBJEKT_ID";
	public static final String HIBERNATE_OBJEKT = "HIBERNATE_OBJEKT";
	
	public HibernateOrderLoad(){
		super(new String[]{HIBERNATE_OBJEKT}, new String[]{HibernateInitSession.HIBERNATE_SESSION, HIBERNATE_OBJEKT_ID}, new String[]{});
	}
	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		Session session = cache.getFromCache(HibernateInitSession.HIBERNATE_SESSION, Session.class);
		Long id = Long.parseLong(cache.getFromCache(HIBERNATE_OBJEKT_ID, String.class));
		Order o = (Order) session.load(Order.class, id);
		if (o == null)
		{
			System.out.println("Objekt mit id = '" + id + "' nicht gefunden!");
		}
		cache.setIntoCache(HIBERNATE_OBJEKT, o);
	}

}
