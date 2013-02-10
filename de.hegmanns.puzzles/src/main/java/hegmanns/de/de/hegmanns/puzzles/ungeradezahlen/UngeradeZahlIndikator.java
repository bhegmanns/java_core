package hegmanns.de.de.hegmanns.puzzles.ungeradezahlen;

/**
 * Hilfsklasse, die zu einer Zahl zurueck gibt, ob sie ungerade ist.
 * Dabei werden verschiedene Verfahren verwendet.
 * 
 * @author B. Hegmanns
 */
public class UngeradeZahlIndikator {

	/**
	 * Ermittelt den Rest der Modulo-Division durch 2.
	 * Ist dieser Rest ungleich 0, so ist die Zahl ungerade.
	 * 
	 * @param zahl
	 * @return
	 */
	public static boolean istUngeradeMitModulo(long zahl){
		return (zahl % 2) != 0;
	}
	
	/**
	 * Ermittelt zunaechst zahl & 1 als Bit-Operation.
	 * <br>
	 * Beispiel:
	 * <pre>
	 * <code>
	 * 	5 & 1 = 
	 *          101 &
	 *          001
	 *          ===
	 *          001 = 1
	 * </code></pre><br>
	 * Der Trick ist, dass durch die Bit-Operation mit 0 alle Stellen wegfallen, mit Ausnahme
	 * der ersten Stelle, die die im Binaerformat einzig moegliche Stelle fuer ungerade Zahlen
	 * darstellt. Alle anderen Stellen sind Potenzen von 2 und damit gerade.
	 * Nur dann, wenn diese Stelle mit 1 und-verknuepft 1 ergibt, ist die Zahl ungerade.
	 * @param zahl
	 * @return
	 */
	public static boolean istUngeradeBitUndZero(long zahl){
		return (zahl & 1) == 1;
	}
}
