package hegmanns.de.de.hegmanns.fundamental.chain;

import java.util.Map;

/**
 * Ein Command fuer einen fachlich franularen Pruefschritt.
 * 
 * Grundsaetzlich soll das PruefCommand so realisiert werden,
 * als wenn die gleiche Instanz wiederverwendet wird und ggf.
 * auch in einer Multithread-Umgebung verwendet wird.
 * 
 * Daher sollen KEINE Instanz-Variablen definiert und
 * verwendet werden.
 * 
 * @author B. Hegmanns
 *
 * @param <T> Typ des zu pruefenden Objekts
 * @param <E> Beschreibt den Typ der Pruefergebnisse (z.B. String)
 */
public interface AusfuehrungsCommand<T, E extends AusfuehrungsContext<T>, R, A extends ReadableAusfuehrungsResult<R>> {

	/**
	 * Fuehrt den Pruefschritt durch, bedient sich fuer wichtige Werte des PruefContext
	 * und legt das Ergebnis im PruefResult ab.
	 * 
	 * <p>
	 * 	<b>Hinweis:</b><br>
	 *  Dem PruefContext kann immer das zu pruefende Objekt entnommen werden,
	 *  mit {@link AusfuehrungsContext#getInstance()}.
	 *  <br><br>
	 *  Dem PruefResult koennen Ergebnisse mit gegeben werden,
	 *  ueber {@link AusfuehrungsResult#add(Object)} oder {@link AusfuehrungsResult#setAusgefuehrt(Boolean)}
	 * </p>
	 * 
	 * 
	 * @param ausfuehrungsContext der PruefContext
	 * @param ausfuehrungsResult das zu beschreibende PruefResult
	 */
	public void execute(E ausfuehrungsContext, A ausfuehrungsResult);
	
	/**
	 * Gibt die von diesem PruefCommand moeglichen Pruefergebnisse zurueck.
	 * 
	 * @return die Map der moeglichen Pruefergebnisse, der Value ist eine verbale Beschreibung.
	 */
	public Map<R, String> getMoeglicheErgebnisse();
}
