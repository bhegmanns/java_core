package hegmanns.de.de.hegmanns.fundamental.chain;

import java.util.List;

/**
 * Bildet das Delegat einer Pruefkette und initialisiert die in eine Pruefkette
 * enthaltenen {@link AusfuehrungsCommand PruefCommands}.
 * @author B. Hegmanns
 *
 * @param <T> Der Typ der zu pruefenden Instanz
 * @param <E> Der im PruefResult zurueck gegebene Typ
 */
public abstract class AusfuehrungskettenFactory<T, E extends AusfuehrungsContext<T>, R, A extends ReadableAusfuehrungsResult<R>> {

	private List<AusfuehrungsCommand<T, E, R, A>> commands;
	protected void addCmd(AusfuehrungsCommand<T, E, R, A> cmd){
		commands.add(cmd);
	}
	/**
	 * Gibt die definierten PruefCommand-Instanzen in der gewuenschten Reihenfolge zurueck.
	 * 
	 * @return
	 */
	public List<AusfuehrungsCommand<T, E, R, A>> getPruefCommands(){
		return commands;
	}
	
	public  abstract AusfuehrungsAdvice<T, E, R, A> createAusfuehrungsadvice(T instanz);
}
