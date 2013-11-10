package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import java.util.Date;

import org.hibernate.Session;

import de.hegmanns.it.schulung.hibernate.domain.order.Order;
import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateOrderSpeichern extends Schritt{
	
	public HibernateOrderSpeichern(){
		super(new String[]{HibernateOrderLoad.HIBERNATE_OBJEKT_ID}, new String[]{HibernateInitSession.HIBERNATE_SESSION}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		Order order = new Order();
		order.setDepotnummer("12345678");
		order.setZeitpunktErstellung(new Date());
		cache.getFromCache(HibernateInitSession.HIBERNATE_SESSION, Session.class).save(order);
		cache.setIntoCache(HibernateOrderLoad.HIBERNATE_OBJEKT_ID, "" + order.getId());
		schrittresult.setProperty("orderid", "" + order.getId());
		System.out.println("Order mit id = '" + order.getId() + "' gespeichert!");
	}

}
