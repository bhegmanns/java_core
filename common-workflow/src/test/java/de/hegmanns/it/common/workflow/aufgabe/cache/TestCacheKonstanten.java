package de.hegmanns.it.common.workflow.aufgabe.cache;

import java.math.BigDecimal;

public class TestCacheKonstanten {

	public static final CacheKonstante<String> A = createCacheKonstante(String.class, "A", "a");
	public static final CacheKonstante<Integer> B = createCacheKonstante(Integer.class, "B", 123);
	public static final CacheKonstante<BigDecimal> C = createCacheKonstante(BigDecimal.class, "C", BigDecimal.TEN);
	
	
	private static <T> CacheKonstante<T> createCacheKonstante(Class<T> typ, String name, T defaultValue)
	{
		return CacheKonstante.create(typ, name, defaultValue);
	}
}
