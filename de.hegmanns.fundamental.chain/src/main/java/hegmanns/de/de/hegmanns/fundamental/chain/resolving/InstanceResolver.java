package hegmanns.de.de.hegmanns.fundamental.chain.resolving;

import hegmanns.de.de.hegmanns.fundamental.chain.AusfuehrungsContext;

/**
 * Ermittelt aus dem uebergebenen Kontext eine weitere Instanz.
 * 
 * Im Allgemeinen delegiert der {@link AusfuehrungsContext} zu mehreren dieser
 * Instanzen zur Ermittlung weiterer Kontext-Objekte.
 * 
 * @author B. Hegmanns
 *
 * @param <I>
 * @param <T>
 */
public interface InstanceResolver<I, T> {

	public I resolve(AusfuehrungsContext<T> pruefContext);
}
