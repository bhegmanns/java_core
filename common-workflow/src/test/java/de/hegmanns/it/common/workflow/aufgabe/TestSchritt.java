package de.hegmanns.it.common.workflow.aufgabe;

import java.math.BigDecimal;

import de.hegmanns.it.common.workflow.aufgabe.Schritt;
import de.hegmanns.it.common.workflow.aufgabe.SchrittResult;
import de.hegmanns.it.common.workflow.aufgabe.cache.Cache;
import de.hegmanns.it.common.workflow.aufgabe.cache.CacheKonstante;
import de.hegmanns.it.common.workflow.aufgabe.cache.TestCacheKonstanten;


/**
 * Ein Schritt fuer JUnit-Tests.
 * 
 * Liest TestCacheKonstanten.A sowie TestCacheKonstanten.B aus dem Cache und schreibt
 * fuer TestCacheKontanten.C den Wert BigDecimal.ONE.
 * 
 * @author B. Hegmanns
 *
 */
public class TestSchritt extends Schritt {
	
	private static final CacheKonstante<?>[] erzeugteKonstanten = {
		TestCacheKonstanten.C
	};
	private static final CacheKonstante<?>[] geleseneKonstanten = {
		TestCacheKonstanten.A, TestCacheKonstanten.B
	};
	private static final CacheKonstante<?>[] geloeschteKonstanten = {};
	
	private BigDecimal wertFuerKonstanteC;

	public TestSchritt(BigDecimal wertFuerKonstanteC){
		super(erzeugteKonstanten, geleseneKonstanten, geloeschteKonstanten);
		this.wertFuerKonstanteC = wertFuerKonstanteC;
	}
	@Override
	protected void schrittAktionAusfuehren(Cache cache,
			SchrittResult schrittresult) {
		
		cache.getFromCache(TestCacheKonstanten.A);
		cache.getFromCache(TestCacheKonstanten.B);
		
		cache.setIntoCache(TestCacheKonstanten.C, wertFuerKonstanteC);
	}

}
