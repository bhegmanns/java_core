package hegmanns.de.de.hegmanns.fundamental.chain;

/**
 * Datenhalter fuer ein komplettes Pruef-Szenario, wird je nach zu pruefenden Objekt weitere Methoden
 * enthalten.
 * 
 * @author B. Hegmanns
 *
 * @param <T>
 */
public interface PruefContext<T> {

	/**
	 * Gibt die Instanz zurueck.
	 * 
	 * @return die Instanz.
	 */
	public T getInstance();
}
