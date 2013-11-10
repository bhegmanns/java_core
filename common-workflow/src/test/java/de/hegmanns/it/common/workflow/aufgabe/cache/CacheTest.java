package de.hegmanns.it.common.workflow.aufgabe.cache;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CacheTest {
	private static final int defaultValue = 7000;
	
	private CacheKonstante<Integer> integerCacheKonstante;
	private Cache cache = new Cache();
	
	@Before
	public void before(){
		integerCacheKonstante = CacheKonstante.create(Integer.class, "MEININT", defaultValue);
	}

	@Test
	public void cacheNichtGesetztGibtDefaultValue(){
		int wert = cache.getFromCache(integerCacheKonstante);
		
		assertThat(wert, is(defaultValue));
	}
	
	@Test
	public void cacheGesetztGibtGecachtenWert(){
		int cachedValue = 123;
		
		cache.setIntoCache(integerCacheKonstante, cachedValue);
		
		int wert = cache.getFromCache(integerCacheKonstante);
		
		assertThat(wert, is(cachedValue));
	}
	
	@Test
	public void cacheGesetztMitNullKonstanteGibtGecachtenWert(){
		int cachedValue = 123;
		
		cache.setIntoCache((CacheKonstante<Integer>)null, cachedValue);
		
		int wert = cache.getFromCache((CacheKonstante<Integer>)null);
		assertThat(wert, is(cachedValue));
	}
	
	@Test
	public void cacheNichtGesetztAbfrageMitNullKonstanteGibtNull(){
		
		Integer wert = cache.getFromCache((CacheKonstante<Integer>)null);
		assertThat(wert, nullValue());
	}
}
