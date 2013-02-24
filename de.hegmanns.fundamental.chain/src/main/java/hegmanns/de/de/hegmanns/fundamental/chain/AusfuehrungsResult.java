package hegmanns.de.de.hegmanns.fundamental.chain;

/**
 * Ein PruefResult steht einem Command individuell zur Verfuegung und wird zur Definition dessen
 * Pruefergebnisses benutzt.
 * 
 * @author B. Hegmanns
 *
 * @param <E> Gibt den Typ des Ergebnisses wieder
 */
public interface AusfuehrungsResult<E> {

	/**
	 * Indiziert die durchgefuehrte Pruefung als erfolgreich ausgefuehrt.
	 * Erfolgreich soll die Pruefung immer dann sein, wenn die Pruefung durchgefuehrt werden konnte.
	 * Dabei ist das eigentlich Ergebnis der Pruefung egal. Auch eine Pruefung mit festgestellten
	 * Fehlern ist eine erfolgreiche durchgefuehrte Pruefung.
	 * 
	 * Eine Pruefung kann beispielsweise dann nicht erfolgreich durchgefuehrt werden, wenn wichtige Informationen
	 * zur Pruefungsdurchfuehrung nicht vorliegen und auch nicht beschafft werden koennen.
	 * 
	 * <p>
	 * <b>Wichtig:</b><br>
	 * Wenn das {@link AusfuehrungsCommand} diese Methode nicht setzt, so wird implizit davon ausgegangen, dass die Pruefung
	 * erfolgreich ausgefuehrt worden ist.
	 * </p>
	 * 
	 * @param ausgefuehrt
	 */
	public void setAusgefuehrt(Boolean ausgefuehrt);
	
	/**
	 * Fuegt dem PruefResult ein Ergebnis hinzu.
	 * Diese Methode kann von einem Command beliebig oft verwendet werden, um beispielsweise mehrere Pruefergebnisse
	 * hinzuzufuegen.
	 * Ob bei mehrfachem Hinzufuegen des gleichen Wertes der Wert auch mehrfach in die Ergebnismenge eingefuegt wird,
	 * haengt vom konkreten Typ E ab.
	 * 
	 * @param ergebnis das hinzuzufuegende Ergebnis
	 */
	public void add(E ergebnis);
}
