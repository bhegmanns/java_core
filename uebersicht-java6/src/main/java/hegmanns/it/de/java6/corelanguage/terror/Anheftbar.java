package hegmanns.it.de.java6.corelanguage.terror;

/**
 * Kennzeichnet irgend etwas an einem Auto anheftbares,
 * beispielsweise einen Aufkleber, ein Nummernschild oder Aehnliches.
 * 
 * @author B. Hegmanns
 *
 */
public interface Anheftbar {

	/**
	 * Beschreibt den Vorgang des Anheftens bzw. Anbringens.
	 * Das Auto an dem diese Instanz angeheftet wird, wird mit uebergeben, damit
	 * diese Instanz sich sein Auto merken kann.
	 * 
	 * @param auto Das Auto, an dem dieses anheftbare Objekt angebracht wird.
	 */
	public void anheften(Auto auto);
}
