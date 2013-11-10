package de.hegmanns.it.schulung.hibernate.gui.action.schritt.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import de.hegmanns.it.schulung.hibernate.domain.order.Order;
import de.hegmanns.it.schulung.hibernate.domain.order.Orderposten;
import de.hegmanns.it.schulung.hibernate.domain.order.Orderteil;
import de.hegmanns.it.schulung.hibernate.domain.order.Ordervorgang;
import de.hegmanns.it.schulung.hibernate.gui.action.Cache;
import de.hegmanns.it.schulung.hibernate.gui.action.Schritt;
import de.hegmanns.it.schulung.hibernate.gui.action.SchrittResult;

public class HibernateInitSessionFactory extends Schritt {
	
	public static final String HIBERNATE_SESSION_FACTORY = "HIBERNATE_SESSION_FACTORY";
	public static final String ISOLATION_LEVEL = "ISOLATION_LEVEL";
	
	private int isolationLevel = 8;
	
	public HibernateInitSessionFactory(){
		super(new String[]{HIBERNATE_SESSION_FACTORY}, new String[]{ISOLATION_LEVEL}, new String[]{});
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult) {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(Orderposten.class);
		configuration.addAnnotatedClass(Orderteil.class);
		configuration.addAnnotatedClass(Ordervorgang.class);
		
		setIsolationLevelFromCache(cache);
		configuration.setProperty("hibernate.connection.isolation", "" + isolationLevel);
		
		cache.setIntoCache(HIBERNATE_SESSION_FACTORY, configuration.configure().buildSessionFactory());
	}
	
	private void setIsolationLevelFromCache(Cache cache)
	{
		try{
			isolationLevel = Integer.parseInt(cache.getFromCache(ISOLATION_LEVEL, String.class));
		}catch(Exception e){
			
		}
	}

}
