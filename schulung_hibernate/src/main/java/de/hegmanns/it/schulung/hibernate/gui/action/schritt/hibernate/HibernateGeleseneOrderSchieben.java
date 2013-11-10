package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import org.apache.commons.lang.time.DateUtils;

import de.hegmanns.it.schulung.hibernate.domain.order.Order;
import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateGeleseneOrderSchieben extends Schritt {
	public static final String ANZAHL_JAHRE = "ANZAHL_JAHRE";
	public static final String ANZAHL_TAGE = "ANZAHL_TAGE";
	private int anzahlTage;
	private int anzahlJahre;
	
	public HibernateGeleseneOrderSchieben(){
		super(new String[]{}, new String[]{HibernateOrderLoad.HIBERNATE_OBJEKT, ANZAHL_JAHRE, ANZAHL_TAGE}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		Order geleseneOrder = cache.getFromCache(HibernateOrderLoad.HIBERNATE_OBJEKT, Order.class);
		setAnzahlFromCache(cache);
		
		geleseneOrder.setZeitpunktErstellung(DateUtils.addDays(geleseneOrder.getZeitpunktErstellung(), anzahlTage));
		geleseneOrder.setZeitpunktErstellung(DateUtils.addYears(geleseneOrder.getZeitpunktErstellung(), anzahlJahre));

	}
	
	private void setAnzahlFromCache(Cache cache)
	{
		String tage = cache.getFromCache(ANZAHL_TAGE, String.class);
		String jahre = cache.getFromCache(ANZAHL_JAHRE, String.class);
		
		
		anzahlTage = parseIntoInt(tage);
		anzahlJahre = parseIntoInt(jahre);
	}
	
	private int parseIntoInt(String value)
	{
		int wert = 0;
		
		try{
		wert = Integer.parseInt(value);
		}catch(Exception e){}
		return wert;
	}

	public int getAnzahlTage() {
		return anzahlTage;
	}

	public void setAnzahlTage(int anzahlTage) {
		this.anzahlTage = anzahlTage;
	}

	public int getAnzahlJahre() {
		return anzahlJahre;
	}

	public void setAnzahlJahre(int anzahlJahre) {
		this.anzahlJahre = anzahlJahre;
	}
	
	

}
