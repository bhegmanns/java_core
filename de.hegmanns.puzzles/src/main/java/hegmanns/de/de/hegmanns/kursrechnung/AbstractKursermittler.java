package hegmanns.de.de.hegmanns.kursrechnung;

import hegmanns.de.de.hegmanns.fundamental.core.Ref;

public abstract class AbstractKursermittler implements Kursermittler {

	/**
	 * Spezielle Implementierung.
	 * Hierbei wird zunaechst die bestmoegliche Kursqualitaet abgefragt.
	 * Falls diese nicht moeglich ist, wird die zurueck gegebene moegliche Kursqualitaet angefragt.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Kurs ermittleKurs(Waehrung ausgangswaehrung, Waehrung zielwaehrung) {
		Ref<Kursermittlungsqualitaet> gewuenschteQualitaet = new Ref<Kursermittlungsqualitaet>(new Kursermittlungsqualitaet(Kursqualitaet.values()[Kursqualitaet.values().length-1]));
		Kurs ermittelterKurs = ermittleKurs(ausgangswaehrung, zielwaehrung, gewuenschteQualitaet);
		
		if (ermittelterKurs == null)
		{
			gewuenschteQualitaet = new Ref<Kursermittlungsqualitaet>(new Kursermittlungsqualitaet(gewuenschteQualitaet.get().moeglicheMindestQualitaet));
			ermittelterKurs = ermittleKurs(ausgangswaehrung, zielwaehrung, gewuenschteQualitaet);
		}
		
		return ermittelterKurs;
	}

}
