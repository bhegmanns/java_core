package de.hegmanns.it.common.workflow.aufgabe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import de.hegmanns.it.common.workflow.aufgabe.cache.Cache;
import de.hegmanns.it.common.workflow.aufgabe.cache.CacheKonstante;

/**
 * Abbildung einer kompletten Aufgabe, die in einer bestimmten Zahl von Einzelschritten (in Form von {@link Schritt}-Instanzen)
 * ausgefuehrt werden soll.
 * 
 * Im Gegensatz zur CommandChain werden hier die einzelnen Aufgabenschritte nicht
 * ununterbrochen ausgefuehrt, sondern jeder Aufgabenschritt muss angetriggert werden,
 * beispielsweise durch einen menschlichen User.
 * 
 * Die einzelnen Aufgabenschritte werden jeweils ueber die Methode {@link #aufgabenschrittAusfuehren()} ausgefuehrt.
 * 
 * @author B. Hegmanns
 */
public class Aufgabe extends Observable implements Observer{
	
	private Set<CacheKonstante<?>> requestedCacheKonstanten = new HashSet<>();
	
	public Set<CacheKonstante<?>> getRequestedParameter(){
		return requestedCacheKonstanten;
	}
	
	public void addRequestedParameter(Set<CacheKonstante<?>> requestedCacheKonstanten){
		this.requestedCacheKonstanten.addAll(requestedCacheKonstanten);
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
		requestedCacheKonstanten = new HashSet<>();
		
		Set<CacheKonstante<?>> vorhandeneKonstanten = new HashSet<>();
		
		for (Schritt schritt : schritte)
		{
			mergeLesende(requestedCacheKonstanten, vorhandeneKonstanten, schritt.getGeleseneKonstanten());
			mergeGeschriebene(vorhandeneKonstanten, vorhandeneKonstanten, schritt.getErzeugteKonstanten());
			mergeGeloeschte(vorhandeneKonstanten, vorhandeneKonstanten, schritt.getGeloeschteKonstanten());
		}
	}
	
	private void mergeLesende(Set<CacheKonstante<?>> requestedCacheKonstanten,Set<CacheKonstante<?>> vorhandeneKonsanten, Set<CacheKonstante<?>> geleseneKonstanten){
		for (CacheKonstante<?> konstante : geleseneKonstanten)
		{
			if (!vorhandeneKonsanten.contains(konstante))
			{
				requestedCacheKonstanten.add(konstante);
			}
		}
	}
	
	private void mergeGeschriebene(Set<CacheKonstante<?>> requestedCacheKonstanten, Set<CacheKonstante<?>> vorhandeneKonsanten, Set<CacheKonstante<?>> geschriebeneKonstanten){
		for (CacheKonstante<?> konstante : geschriebeneKonstanten)
		{
			vorhandeneKonsanten.add(konstante);
		}
	}

	private void mergeGeloeschte(Set<CacheKonstante<?>> requestedCacheKonstanten, Set<CacheKonstante<?>> vorhandeneKonsanten, Set<CacheKonstante<?>> geloeschteKonstanten){
		for (CacheKonstante<?> konstante : geloeschteKonstanten)
		{
			vorhandeneKonsanten.remove(konstante);
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
