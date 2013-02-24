package hegmanns.de.de.hegmanns.fundamental.chain.resolving;

/**
 * Bindeglied zur automatischen Registrierung von {@link InstanceResolver}-Instanzen.
 * Diese Klasse <b>muss</b> mit {@link ResolveForType} annotiiert werden.
 * 
 * Ueber den Selbst-Registrierungsmechanismus findet der PruefContext dann immer den
 * richtigen Resolver zur Ermittlung von Instanzwerten.
 * 
 * @author B. Hegmanns
 *
 * @param <I>
 * @param <T>
 */
public abstract class AbstractInstanceResolver<I, T> implements InstanceResolver<I, T> {
	
	protected AbstractInstanceResolver(){
		ResolverRegistry.register(createInstance());
	}

	/**
	 * Ueberschreibe diese Methode soll die Instanz der Subklasse
	 * zurueck geben.
	 * Im einfachsten Fall <code>return this;</code>.
	 * @return
	 */
	protected abstract AbstractInstanceResolver createInstance();
}
