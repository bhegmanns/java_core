package de.hegmanns.it.utils.core.wertebereich;

import java.util.Date;

/**
 * Wertebereich fuer Datumswerte, also eine Epoche.
 * 
 * @author B. Hegmanns
 *
 */
public class Zeitraum extends Wertebereich<Date>{

	public Zeitraum(WertebereichValue<Date> start, boolean startwertEnthalten,
			WertebereichValue<Date> ende, boolean endewertEnthalten) {
		super(start, startwertEnthalten, ende, endewertEnthalten);
	}
	
}
