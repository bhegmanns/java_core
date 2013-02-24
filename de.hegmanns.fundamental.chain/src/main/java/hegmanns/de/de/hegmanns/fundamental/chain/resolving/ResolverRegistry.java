package hegmanns.de.de.hegmanns.fundamental.chain.resolving;

import java.util.HashMap;
import java.util.Map;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsContext;

public final class ResolverRegistry {

	private static final Map<String, AbstractInstanceResolver> map = new HashMap<String, AbstractInstanceResolver>();
	
	public static void register(AbstractInstanceResolver abstractInstanceResolver){
		ResolveForType resolveForType = abstractInstanceResolver.getClass().getAnnotation(ResolveForType.class);
		if (resolveForType == null)
		{
			throw new IllegalArgumentException("An Klasse '" + abstractInstanceResolver.getClass() + "' fehlt die Annotation ResolveForType!");
		}
		else
		{
			Class<?> instanceType = resolveForType.instanceType();
			Class<?> resolveType = resolveForType.resolveType();
			System.out.println("Registrierung fuer '" + instanceType + "', '" + resolveType + "': " + abstractInstanceResolver);
			map.put(instanceType.getName() + ";" + resolveType.getName(), abstractInstanceResolver);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <I, T> AbstractInstanceResolver<I, T> getResolver(Class<I> type, AusfuehrungsContext<T> pruefcontext)
	{
		return map.get(type.getName() + ";" + pruefcontext.getInstance().getClass());
	}
}

