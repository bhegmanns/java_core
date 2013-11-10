package de.hegmanns.it.schulung.hibernate.gui.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

public abstract class Schritt extends Observable{
	
	
	private Set<String> geleseneKonstanten = new HashSet<String>();
	private Set<String> erzeugteKonstanten = new HashSet<String>();
	private Set<String> geloeschteKonstanten = new HashSet<String>();
	
	protected Schritt(){
		
	}
	
	protected Schritt(String[] erzeugteKonstanten, String[] geleseneKonstanten, String[] geloeschteKonstanten)
	{
		this.geleseneKonstanten.addAll(Arrays.asList(geleseneKonstanten));
		this.erzeugteKonstanten.addAll(Arrays.asList(erzeugteKonstanten));
		this.geloeschteKonstanten.addAll(Arrays.asList(geloeschteKonstanten));
	}

	public void schrittAusfuehren(Cache cache){
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
	
	protected abstract void schrittAktionAusfuehren(Cache cache, SchrittResult schrittresult);
	
	@Override
	public String toString() {
		return "Schritt [" + this.getClass().getSimpleName() + ":" + additionalToString() + "]";
	}
	
	public String additionalToString(){
		return "";
	}
	public Set<String> getGeleseneKonstanten() {
		return geleseneKonstanten;
	}
	public Set<String> getErzeugteKonstanten() {
		return erzeugteKonstanten;
	}
	
	
	
	
}
