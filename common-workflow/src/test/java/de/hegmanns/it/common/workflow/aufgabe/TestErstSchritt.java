package de.hegmanns.it.common.workflow.aufgabe;

import de.hegmanns.it.common.workflow.aufgabe.cache.Cache;
import de.hegmanns.it.common.workflow.aufgabe.cache.CacheKonstante;
import de.hegmanns.it.common.workflow.aufgabe.cache.TestCacheKonstanten;

/**
 * Ein Schritt fuer JUnit-Test.
 * 
 * Schreibt fuer TestCacheKonstanten.A den Wert "A" in den Cache.
 * 
 * @author B. Hegmanns
 *
 */
public class TestErstSchritt extends Schritt {
	
	private static final CacheKonstante<?>[] erzeugteKonstanten = {
		TestCacheKonstanten.A
	};
	private static final CacheKonstante<?>[] geleseneKonstanten = {
	};
	private static final CacheKonstante<?>[] geloeschteKonstanten = {};
	
	public TestErstSchritt(){
		super(erzeugteKonstanten, geleseneKonstanten, geloeschteKonstanten);
	}

	@Override
	protected void schrittAktionAusfuehren(Cache cache,
			SchrittResult schrittresult) {
		
		cache.setIntoCache(TestCacheKonstanten.A, "A");
	}

}
