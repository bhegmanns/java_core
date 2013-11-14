package de.hegmanns.it.common.commandchain;

import java.util.Map;

public class CommandContext<U> {

	private U instanz;
	
	private Map<String, Object> cache;
	
	public CommandContext(U instanz)
	{
		this.instanz = instanz;
	}
	
	protected void setInstanz(U instanz){
		this.instanz = instanz;
	}
	
	public U getInstanz(){
		return instanz;
	}
	
	protected Object getFromCache(String name){
		return cache.get(name);
	}
	
	@SuppressWarnings("unchecked")
	protected <A> A getFromCache(String name, Class<A> typ)
	{
		return (A)getFromCache(name);
	}
	
	protected void setIntoCache(String name, Object o)
	{
		cache.put(name, o);
	}
	
	protected Object resolveFromCache(String name, ContextResolver resolver){
		if (!cache.keySet().contains(name))
		{
			Object value = resolver.resolve(getInstanz());
			cache.put(name, value);
			
			return value;
		}else{
			return cache.get(name);
		}
	}
	
	
}
