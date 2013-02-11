package hegmanns.de.de.hegmanns.fundamental.chain;

import java.util.List;

/**
 * Bildet das Delegat einer Pruefkette und initialisiert die in eine Pruefkette
 * enthaltenen {@link PruefCommand PruefCommands}.
 * @author B. Hegmanns
 *
 * @param <T> Der Typ der zu pruefenden Instanz
 * @param <E> Der im PruefResult zurueck gegebene Typ
 */
public interface PruefkettenFactory<T, E> {

	/**
	 * Gibt die definierten PruefCommand-Instanzen in der gewuenschten Reihenfolge zurueck.
	 * 
	 * @return
	 */
	public List<PruefCommand<T, E>> getPruefCommands();
	
	/**
	 * Erstellt/initialisiert den PruefContext.
	 * 
	 * @return
	 */
	public PruefContext<T> createPruefContext();
	
	/**
	 * Erstellt ein neues PruefResult.
	 * 
	 * @return
	 */
	public PruefResult<E> createPruefResult();
}
