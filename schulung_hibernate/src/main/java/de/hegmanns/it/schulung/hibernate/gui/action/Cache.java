package de.hegmanns.it.schulung.hibernate.gui.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cache {

	private Set<String> keys = new HashSet<String>();
	private Map<String, Object> cacheImpl = new HashMap<String, Object>();
	
	void addKey(String name)
	{
		if (!keys.contains(name))
		{
			keys.add(name);
		}
		else
		{
			throw new RuntimeException("Name '" + name + "' bereits als Key aufgenommen");
		}
	}
	
	
	
	private void checkName(String name){
//		if (!keys.contains(name))
//		{
//			throw new RuntimeException("Name '" + name + "' unbekannt!");
//		}
	}
	
	public boolean isSet(String name)
	{
		checkName(name);
		
		return cacheImpl.get(name) != null;
	}
	public void setIntoCache(String name, Object o)
	{
		checkName(name);
		
		cacheImpl.put(name, o);
	}
	
	public Object getFromCache(String name)
	{
		checkName(name);
		
		return cacheImpl.get(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getFromCache(String name, Class<T> type)
	{
		Object o = getFromCache(name);
		
		return (T)o;
	}
	
	public void removeFromCache(String name){
		checkName(name);
		cacheImpl.remove(name);
	}
	
	public void removeFromCache(String name, Object o)
	{
		checkName(name);
		Object cachedObject = getFromCache(name);
		if (o != cachedObject)
		{
			throw new RuntimeException("Object im Cache und uebergebenes Object sind nicht gleich!");
		}
		
		removeFromCache(name);
	}
}
