package de.hegmanns.it.utils.builder;

import java.util.HashMap;
import java.util.Map;



/**
 * Registry fuer Subklassen von {@link Builder}.
 * 
 * An dieser Registry registrieren sich automatisch TestdatenBuilder-Subklassen.
 * 
 * @author B. Hegmanns
 *
 */
public class BuilderRegistry {

	private static Map<Class<?>, Builder<?>> testdatenbuildermap = new HashMap<Class<?>, Builder<?>>();
	
	public static void register(Class<?> type, Builder<?> builder)
	{
		synchronized(BuilderRegistry.class)
		{
			if (testdatenbuildermap.get(type) == null)
			{
				testdatenbuildermap.put(type, builder);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Builder<T> gatherTestdatenBuilder(Class<T> type)
	{
		Builder<T> builder = null;
		
		builder = (Builder<T>) testdatenbuildermap.get(type);
		
		return builder;
	}
	
	public static Map<Class<?>, Builder<?>> getRegisteredTestdatenBuilderMap()
	{
		return testdatenbuildermap;
	}
}
