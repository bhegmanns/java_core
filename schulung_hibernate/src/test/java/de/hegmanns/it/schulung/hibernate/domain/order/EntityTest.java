package de.hegmanns.it.schulung.hibernate.domain.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.hegmanns.test.Component;

@Category(Component.class)
public class EntityTest {

	private static SessionFactory sessionFactory = null;
	
	@BeforeClass
	public static void initialisiere(){
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(Orderposten.class);
		configuration.addAnnotatedClass(Orderteil.class);
		configuration.addAnnotatedClass(Ordervorgang.class);
		
		
		configuration.configure();
		
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void orderAnlegenSpeichernAendern(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Order order = new Order();
		order.setDepotnummer("12345678");
		order.setZeitpunktErstellung(new Date());
		session.save(order);
		
		long id = order.getId();
		tx.commit();
		
		tx = session.getTransaction();
		tx.setTimeout(0);
		tx.begin();
		Order geleseneOrder = (Order) session.load(Order.class, id);
		geleseneOrder.setZeitpunktErstellung(DateUtils.addDays(geleseneOrder.getZeitpunktErstellung(), 20));
		session.saveOrUpdate(order);;
		tx.commit();
		session.close();
	}
	
	@Test
	public void foo(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Order order = new Order();
		order.setDepotnummer("12345678");
		order.setZeitpunktErstellung(new Date());
		session.save(order);
		
		long id = order.getId();
		tx.commit();
		
		tx = session.getTransaction();
		tx.setTimeout(0);
		tx.begin();
		Order geleseneOrder = (Order) session.load(Order.class, id);
		geleseneOrder.setZeitpunktErstellung(DateUtils.addDays(geleseneOrder.getZeitpunktErstellung(), 20));
		session.saveOrUpdate(order);
		Session neueSession = sessionFactory.openSession();
		MatcherAssert.assertThat("", neueSession, Matchers.not(Matchers.is(session)));
		Transaction txNeu = neueSession.beginTransaction();
		Order neueGeleseneOrder = (Order) session.load(Order.class, id);
//		MatcherAssert.assertThat("", geleseneOrder, Matchers.not(Matchers.is(neueGeleseneOrder)));
		neueGeleseneOrder.setZeitpunktErstellung(DateUtils.addMonths(neueGeleseneOrder.getZeitpunktErstellung(), 6));
		
		txNeu.commit();
		neueSession.close();
		tx.commit();
		session.close();
	}
	
	@Test
	public void orderAnlegenUndBulkLoeschen(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Order order = createOrder();
		session.saveOrUpdate(order);
		long id = order.getId();
		System.out.println("Angelegte Order = " + id);
		tx.commit();
		session.close();
		
		try{
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Query query = session.createQuery("delete from Order o where o.id = " + id);
		System.out.println("Order geloescht");
		query.executeUpdate();
		
		tx.commit();
		Assert.fail("Hier muss ein Fehler auftreten ...");
		}catch(Exception e){
			tx = session.beginTransaction();
			order = (Order) session.get(Order.class, id);
			
			session.delete(order);
			
			tx.commit();
		}
		session.close();
	}
	
	@Test
	public void orderMitOrderpostenAnlegenUndAendern(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Order order = new Order();
		order.setDepotnummer("12345678");
		order.setZeitpunktErstellung(new Date());
		session.save(order);
		
		Orderposten orderposten = new Orderposten();
		orderposten.setHandelswert(BigDecimal.ONE);;
		orderposten.setWkn("710000");
		List<Orderposten> postens = new ArrayList<Orderposten>();
		postens.add(orderposten);
		order.setOrderposten(postens);
		session.save(orderposten);
		session.saveOrUpdate(order);
		
		long id = order.getId();
		tx.commit();
		
		tx = session.getTransaction();
		tx.setTimeout(0);
		tx.begin();
		Order geleseneOrder = (Order) session.load(Order.class, id);
		geleseneOrder.setZeitpunktErstellung(DateUtils.addDays(geleseneOrder.getZeitpunktErstellung(), 20));
		session.saveOrUpdate(order);;
		tx.commit();
		session.close();
	}
	
	@Test
	public void vorgangAnlegenSpeichernAendern(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Ordervorgang vorgang = new Ordervorgang();
		session.save(vorgang);
		
		long id = vorgang.getId();
		
		tx.commit();
		
		tx = session.getTransaction();
		tx.setTimeout(0);
		tx.begin();
		
		Ordervorgang gelesenerVorgang = (Ordervorgang)session.load(Ordervorgang.class, id);
		Ordervorgang v = new Ordervorgang();
		v.setInternerVorgang(gelesenerVorgang);
		session.save(v);
		session.saveOrUpdate(gelesenerVorgang);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void orderMitOrderpostenAnlegen(){
		
		Session session = sessionFactory.openSession();
		
		
		List<Query> queries = new ArrayList<Query>();
		queries.add(session.createQuery("delete from Ordervorgang ov"));
		queries.add(session.createQuery("delete from Orderposten op"));
		queries.add(session.createQuery("delete from Order o where o.id = 27234"));
		long startZeit = System.currentTimeMillis();
		Transaction tx = session.beginTransaction();
		for (Query query : queries)
		{
			query.executeUpdate();
		}
		tx.commit();
		session.close();
		session = sessionFactory.openSession();
		long endZeit = System.currentTimeMillis();
		System.out.println("Zeit = " + (endZeit - startZeit));
		tx = session.beginTransaction();
		
		for (int i = 0 ; i < 1000 ; i++)
		{
			Order order = createOrder();
			
//			session.save(order.getOrderposten().get(0));
			session.saveOrUpdate(order);
			
//			session.flush();
			System.out.println("Order " + i);
		}
		
		tx.commit();
		session.close();
	}
	
	
	private Order createOrder(){
		Order order = new Order();
		
		order.setOrderposten(new ArrayList<Orderposten>());
		order.getOrderposten().add(createOrderposten());
		order.setDepotnummer(createDepotnummer());
		
		return order;
	}
	
	private Orderposten createOrderposten(){
		Orderposten orderposten = new Orderposten();
		
		orderposten.setHandelswert(createHandelswert());
		orderposten.setWkn(createWkn());
		
		return orderposten;
	}
	
	private BigDecimal createHandelswert(){
		int handelswert = 1 + (int)(Math.random() * 1000);
		return new BigDecimal(handelswert);
	}
	
	private String createString(int anzahlStellen, boolean nurZiffern)
	{
		StringBuffer buffer = new StringBuffer("");
		
		for (int i = 0 ; i < anzahlStellen ; i++)
		{
			int ziffer = (int)(Math.random() * 10);
			buffer.append("" + ziffer);
		}
		
		return buffer.toString();
	}
	
	private String createWkn(){
		return createString(6, true);
	}
	
	private String createDepotnummer(){
		return createString(10, true);
	}
}
