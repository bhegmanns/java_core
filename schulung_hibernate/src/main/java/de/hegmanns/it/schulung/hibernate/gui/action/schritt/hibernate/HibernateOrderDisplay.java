package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import de.hegmanns.it.schulung.hibernate.domain.order.Order;
import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateOrderDisplay extends Schritt {
	
	public HibernateOrderDisplay(){
		super(new String[]{HibernateOrderLoad.HIBERNATE_OBJEKT}, new String[]{}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache,
			SchrittResult schrittresult) {
		Order order = cache.getFromCache(HibernateOrderLoad.HIBERNATE_OBJEKT, Order.class);
		System.out.println("" + order.toString());
	}

}
