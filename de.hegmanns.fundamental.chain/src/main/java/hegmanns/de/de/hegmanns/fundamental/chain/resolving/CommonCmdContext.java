package hegmanns.de.de.hegmanns.fundamental.chain.resolving;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Spezieller PruefContext, der sich der ResolverRegistry bedient und so
 * Werte fast automatisch ermittelt kann.
 * 
 * Fuer eigene Klassen muss nur noch der Typ T bestimmt werden, so dass
 * es fuer jedes relevante Objekt typischerweise eine eigene Unterklasse
 * gibt.
 * 
 * @author B. Hegmanns
 *
 * @param <T>
 */
public class CommonCmdContext<T> extends AusfuehrungsContext<T> {

	private Map<Class<?>, Object> instanceCache = new HashMap<Class<?>, Object>();
	private Map<Class<?>, Boolean> cached = new HashMap<Class<?>, Boolean>();
	
	public CommonCmdContext(T instance){
		super(instance);
	}
	
	protected <U> InstanceResolver<U, T> getInstanceResolver(Class<U> type){
		return ResolverRegistry.getResolver(type, this);
	}
	
	protected <U> U resolve(Class<U> type){
		InstanceResolver<U, T> resolver = getInstanceResolver(type);
		if (resolver != null)
		{
			return resolver.resolve(this);
		}
		else
		{
			throw new IllegalArgumentException("Kein Resolver registriert fuer '" + type.getName() + "'");
		}
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <U> U gatherValue(Class<U> type){
		Boolean gemarkt = cached.get(type);
		if (gemarkt != null && Boolean.FALSE.equals(gemarkt)){
			return resolve(type);
		}
		else
		{
			if (gemarkt == null)
			{
				U instanz = resolve(type);
				cached.put(type, Boolean.TRUE);
				instanceCache.put(type, instanz);
				return instanz;
			}else
			{
				return (U) instanceCache.get(type);
			}
		}
	}
	
	public <U> U getValue(Class<U> type){
		return gatherValue(type);
	}
	
	/**
	 * Setzt in diesen Context den Wert fuer einen bestimmten Typ.
	 * Dieser Wert ist dann auch gecached und wird - so lange diese Context-Instanz
	 * existiert - mit {@link #getInstance()} besorgt.
	 * 
	 * @param type
	 * @param value
	 */
	public <U> void setValue(Class<U> type, U value){
		cached.put(type, Boolean.TRUE);
		instanceCache.put(type, value);
	}
	
	public <U> void setUncachable(Class<U> type, Boolean uncachable){
		cached.put(type, !uncachable);
	}
	
	public <U> void clear(Class<U> type){
		cached.remove(type);
		instanceCache.remove(type);
	}
	
	public void clearCache(){
		cached = new HashMap<Class<?>, Boolean>();
		instanceCache = new HashMap<Class<?>, Object>();
	}

}
