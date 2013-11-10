package de.hegmanns.it.schulung.hibernate.gui.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

/**
 * Abbildung einer kompletten Aufgabe, die in einer bestimmten Zahl von Einzelschritten
 * ausgefuehrt werden soll.
 * 
 * @author B. Hegmanns
 */
public class Aufgabe extends Observable implements Observer{
	
	private Map<String, String> requestedParameter = new HashMap<String, String>();
	
	public Map<String, String> getRequestedParameter(){
		return requestedParameter;
	}
	
	public void setRequestedParameter(Map<String, String> parameter){
		this.requestedParameter.putAll(parameter);
	}
	public void addParameter(Properties properties){
		for (Entry<Object, Object> propertyEnry : properties.entrySet())
		{
			cache.setIntoCache(propertyEnry.getKey().toString(), propertyEnry.getValue());
		}
	}

	private List<Schritt> schritte = new ArrayList<Schritt>();
	private Iterator<Schritt> schrittIterator = null;
	private Cache cache = null;
	
	public Aufgabe(List<Schritt> schritte)
	{
		this.schritte = schritte;
		cache = new Cache();
		initRequestedParameter();
	}
	
	/**
	 * Initialisiert die Request-Parameter, die von aussen definiert werden muessen.
	 */
	private void initRequestedParameter(){
		requestedParameter = new HashMap<String, String>();
		
		for (Schritt schritt : schritte)
		{
			
		}
	}
	
	private boolean schrittGestartet = false;
	
	public synchronized boolean aufgabenschrittAusfuehren(){
		
		if (schrittGestartet)
		{
			return false;
		}
		
		
		if (getSchrittIterator().hasNext())
		{
			this.setChanged();
			
			this.notifyObservers(new InfoStatus(AufgabenStatus.IN_WORK, new SchrittResult()));
			final Schritt schritt = getSchrittIterator().next();
			System.out.println(">>> " + schritt.toString());
			schritt.addObserver(this);
			
			Thread thread = new Thread(){

				@Override
				public void run() {
					schritt.schrittAusfuehren(getCache());
				}
				
			};
			
			thread.start();
			
		}
		
		if (!getSchrittIterator().hasNext())
		{
			System.out.println("FERTICH");
//			this.setChanged();
//			this.notifyObservers("READY WORK");
		}
		schrittGestartet = true;
		return true;
	}
	public synchronized List<Schritt> getAufgabenschritte() throws IllegalAccessException
	{
		if (schrittIterator != null)
		{
			throw new IllegalAccessException("Aufgabe ist bereits in Ausfuehrung.");
		}
		
		return schritte;
	}
	public synchronized List<String> getAufgabenliste() throws IllegalAccessException{
		if (schrittIterator != null)
		{
			throw new IllegalAccessException("Aufgabe ist bereits in Ausfuehrung.");
		}
		
		List<String> aufgabenliste = new ArrayList<String>();
		
		for (Schritt schritt : schritte)
		{
			aufgabenliste.add(schritt.toString());
		}
		
		return aufgabenliste;
	}
	
	private Iterator<Schritt> getSchrittIterator()
	{
		if (schrittIterator == null)
		{
			schrittIterator = schritte.iterator();
		}
		
		return schrittIterator;
	}
	
	public Cache getCache(){
		if (cache == null)
		{
			cache = new Cache();
		}
		
		return cache;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (o instanceof Schritt)
		{
			
			schrittGestartet = false;
			Schritt schritt = (Schritt) o;
			schritt.deleteObserver(this);
			SchrittResult schrittresult = (SchrittResult)arg;
			
			setChanged();
			if (getSchrittIterator().hasNext())
			{
			notifyObservers(new InfoStatus(AufgabenStatus.WAITING_FOR_WORK, schrittresult));
			}else
			{
				notifyObservers(new InfoStatus(AufgabenStatus.ALL_DONE, schrittresult));
			}
		}
		
	}
}
