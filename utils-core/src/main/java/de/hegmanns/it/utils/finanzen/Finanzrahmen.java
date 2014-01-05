package de.hegmanns.it.utils.finanzen;

import de.hegmanns.it.utils.core.wertebereich.Wertebereich;
import de.hegmanns.it.utils.core.wertebereich.WertebereichValue;

/**
 * Spezieller Wertebereich fuer Geldbetraege gleicher Waehrung.
 * 
 * @author B. Hegmanns
 */
public class Finanzrahmen extends Wertebereich<Geldbetrag>{

	
	public Finanzrahmen(Geldbetrag start,
			boolean startwertEnthalten, Geldbetrag ende,
			boolean endewertEnthalten) {
		super(new WertebereichValue<>(start), startwertEnthalten, new WertebereichValue<>(ende), endewertEnthalten);
		if (!start.getWaehrung().equals(ende.getWaehrung()))
		{
			throw new IllegalArgumentException("Waehrungen stimmen nicht ueberein.");
		}
	}
	
	public Finanzrahmen(Geldbetrag start, boolean startwertEnthalten, Geldbetrag ende ,boolean endewertEnthalten, Kurs kurs)
	{
		this(start, startwertEnthalten, kurs.berechne(ende), endewertEnthalten);
	}
	
	public Finanzrahmen(Geldbetrag start, boolean startwertEnthalten, Geldbetrag ende, boolean endewertEnthalten, KursProvider kursprovider)
	{
		this(start, startwertEnthalten, kursprovider.getKurs(start.getWaehrung(), ende.getWaehrung()).berechne(ende), endewertEnthalten);
	}

	
	
}
