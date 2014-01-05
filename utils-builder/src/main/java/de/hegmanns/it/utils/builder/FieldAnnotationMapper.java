package de.hegmanns.it.utils.builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import de.hegmanns.it.utils.core.Pair;

/**
 * Liest Runtime-Annotationen.
 * 
 * @author B. Hegmanns
 */
public class FieldAnnotationMapper {

	public static <A extends Annotation> Map<String, Object> read(Object o, Class<A> annotationType)
	{
		Field[] fields = o.getClass().getFields();
		
		Map<String, Object> memberDefinitions = new HashMap<String, Object>();
		
		for (Field field : fields)
		{
			Pair<String, Object> definition = readField(o, field, annotationType);
			if (definition != null)
			{
			memberDefinitions.put(definition.getFirst(), definition.getSecond());
			}
		}
		
		return memberDefinitions;
	}
	
	@SuppressWarnings("unchecked")
	private static <A extends Annotation> Pair<String, Object> readField(Object o, Field field, Class<A> annotationType){
		A annotation = field.getAnnotation(annotationType);
		Pair<String, Object> pair = null;
		
		
		try {
			if (annotation != null)
			{
				Class<?> type = field.getType();
				String name = null;
				Object value = null;
				if (type.equals(Pair.class))
				{
					Pair<String, Object> currentPair = (Pair<String, Object>) field.get(o);
					name = currentPair.getFirst();
					value = currentPair.getSecond();
				}
				else
				{
				name  = field.getName();
				value = field.get(o);
				}
				pair = new Pair<String, Object>(name, value);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			
		} 
		
		return pair;
	}
}
