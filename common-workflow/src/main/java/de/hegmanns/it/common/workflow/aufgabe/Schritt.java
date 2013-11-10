package de.hegmanns.it.common.workflow.aufgabe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import de.hegmanns.it.common.workflow.aufgabe.cache.Cache;
import de.hegmanns.it.common.workflow.aufgabe.cache.CacheKonstante;

public abstract class Schritt extends Observable{
	
	
	
	private Set<CacheKonstante<?>> erzeugteKonstanten = new HashSet<>();
	private Set<CacheKonstante<?>> geleseneKonstanten = new HashSet<>();
	private Set<CacheKonstante<?>> geloeschteKonstanten = new HashSet<>();
	
	protected Schritt(){
		
	}
	/**
	 * super(new CacheKonstante<?>[]{erzeugteKonstanten}, new CacheKonstante<?>[]{geleseneKonstanten}, new CacheKonstante<?>[]{geloeschteKonstanten});
	 * @param erzeugteKonstanten
	 * @param geleseneKonstanten
	 * @param geloeschteKonstanten
	 */
	protected Schritt(CacheKonstante<?>[] erzeugteKonstanten, CacheKonstante<?>[] geleseneKonstanten, CacheKonstante<?>[] geloeschteKonstanten)
	{
		this.erzeugteKonstanten = new HashSet<>(Arrays.asList(erzeugteKonstanten));
		this.geleseneKonstanten = new HashSet<>(Arrays.asList(geleseneKonstanten));
		this.geloeschteKonstanten = new HashSet<>(Arrays.asList(geloeschteKonstanten));
	}
	
	

	public final void schrittAusfuehren(Cache cache){
		SchrittResult schrittresult = new SchrittResult();
		try{
			schrittAktionAusfuehren(cache, schrittresult);
		}catch(Throwable t)
		{
			t.printStackTrace();
		}
		this.setChanged();
		this.notifyObservers(schrittresult);
	}
	
	/**
	 * 
	 * 
	 * @param cache
	 * @param schrittresult
	 */
	protected abstract void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult);
	
	@Override
	public String toString() {
		return "Schritt [" + this.getClass().getSimpleName() + ":" + additionalToString() + "]";
	}
	
	public String additionalToString(){
		return "";
	}
	public Set<CacheKonstante<?>> getGeleseneKonstanten() {
		return geleseneKonstanten;
	}
	public Set<CacheKonstante<?>> getErzeugteKonstanten() {
		return erzeugteKonstanten;
	}
	public Set<CacheKonstante<?>> getGeloeschteKonstanten() {
		return geloeschteKonstanten;
	}
	
	
	
	
}
