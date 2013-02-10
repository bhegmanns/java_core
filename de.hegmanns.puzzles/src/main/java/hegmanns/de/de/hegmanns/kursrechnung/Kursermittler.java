package hegmanns.de.de.hegmanns.kursrechnung;

import hegmanns.de.de.hegmanns.fundamental.core.Ref;

/**
 * Ermittelt den {@link Kurs} zwischen Ausgangswaehrung und Zielwaehrung in Form eines Multiplikators.
 * 
 * @author B. Hegmanns
 */
public interface Kursermittler {
	
	/**
	 * Gibt den ermittelten Kurs zwischen Ausgangswaehrung und Zielwaehrung in der gewuenschten Mindestqualitaet zurueck.
	 * 
	 * Falls der Kurs in der gewuenschten Mindestqualitaet nicht zurueck gegeben werden kann, jedoch eine hoehere, so wird
	 * dieser Kurs zurueck gegeben.<br>
	 * Falls der Kurs in der gewuenschten Mindestqualitaet (oder hoeher) nicht zurueck gegeben werden kann,
	 * wird <i>null</i> zurueck gegeben.
	 * Die moegliche Kursqualitaet findet sich dann in {@link Kursermittlungsqualitaet#getMoeglicheMindestQualitaet()}
	 * 
	 * 
	 * @param ausgangswaehrung
	 * @param zielwaehrung
	 * @param kursermittlungsqualitaet
	 * @return der Kurs in der Mindestqualitaet oder <i>null</i>.
	 */
	public Kurs ermittleKurs(Waehrung ausgangswaehrung, Waehrung zielwaehrung, Ref<Kursermittlungsqualitaet> kursermittlungsqualitaet);
	
	/**
	 * Gibt den ermittelten Kurs in der bestmoeglichen Qualitaet zurueck.
	 * 
	 * @param ausgangswaehrung die Ausgangswaehrung
	 * @param zielwaehrung die Zielwaehrung
	 * @return der ermittelte Kurs, enthaelt die Qualitaet
	 */
	public Kurs ermittleKurs(Waehrung ausgangswaehrung, Waehrung zielwaehrung);
}
