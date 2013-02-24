package hegmanns.de.de.hegmanns.fundamental.chain.resolving;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mit dieser Annotation muessen Subklassen von {@link AbstractInstanceResolver} annotiiert werden,
 * um anzugeben, was genau diese Klasse ermittelt.
 * 
 * @author B. Hegmanns
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ResolveForType {
	Class<?> resolveType();
	Class<?> instanceType();
}
