package de.hegmanns.it.common.workflow.aufgabe.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Ein simpler auf Konstanten beruhender Cache.
 * 
 * @author B. Hegmanns
 */
public class Cache {
	private  Map<CacheKonstante<?>, Object> cacheKonstImpl = new HashMap<>();
	
	
	public <T> T getFromCache(CacheKonstante<T> cacheKonstante){
		@SuppressWarnings("unchecked")
		T value = (T)cacheKonstImpl.get(cacheKonstante);
		
		if (value == null)
		{
			value = getDefaultValueFrom(cacheKonstante);
		}
		
		return value;
	}
	
	public <T> void setIntoCache(CacheKonstante<T> cacheKonstante, T value)
	{
		cacheKonstImpl.put(cacheKonstante, value);
	}
	
	public <T> void removeFromCache(CacheKonstante<T> cacheKonstante)
	{
		cacheKonstImpl.remove(cacheKonstante);
	}
	
	private <T> T getDefaultValueFrom(CacheKonstante<T> cacheKonstante){
		return cacheKonstante != null ? cacheKonstante.getDefaultValue() : null;
	}
}
