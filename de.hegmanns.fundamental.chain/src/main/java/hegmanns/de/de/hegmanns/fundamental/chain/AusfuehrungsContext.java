package hegmanns.de.de.hegmanns.fundamental.chain;

/**
 * Datenhalter fuer ein komplettes Ausfuehrungs-Szenario,
 * wird je nach zu Objekt weitere Methoden
 * enthalten.
 * 
 * @author B. Hegmanns
 *
 * @param <T>
 */
public class AusfuehrungsContext<E> {
	
	private E instanz;
	public AusfuehrungsContext(E instanz){
		this.instanz = instanz;
	}

	/**
	 * Gibt die Instanz zurueck.
	 * 
	 * @return die Instanz.
	 */
	public E getInstance(){
		return instanz;
	}
}
