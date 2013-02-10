package hegmanns.de.de.hegmanns.kursrechnung;

import java.math.BigDecimal;

import hegmanns.de.de.hegmanns.fundamental.core.Assertation;

/**
 * Beschreibt einen Kurs, den Multiplikator zwischen Ausgangs- und Zielwaehrung sowie die Kursqualitaet.
 * 
 * @author B. Hegmanns
 */
public class Kurs {

	/**
	 * Die Ausgangswaehrung (Herkunft-Waehrung)
	 */
	private Waehrung ausgangswaehrung;
	
	/**
	 * Die Zielwaehrung
	 */
	private Waehrung zielwaehrung;
	
	/**
	 * Der Multiplikator zwischne Ausgangs- und Zielwaehrung, also
	 * <code>
	 * 	betragInAusgangswaehrung * multiplikator = betragInZielwaehrung
	 * </code>
	 */
	private BigDecimal multiplikator;
	
	/**
	 * Die Kursqualitaet, beschreibt die Herkunft des Kurses.
	 */
	private Kursqualitaet kursqualitaet;
	
	public Waehrung getAusgangswaehrung() {
		return ausgangswaehrung;
	}
	public void setAusgangswaehrung(Waehrung ausgangswaehrung) {
		this.ausgangswaehrung = ausgangswaehrung;
	}
	public Waehrung getZielwaehrung() {
		return zielwaehrung;
	}
	public void setZielwaehrung(Waehrung zielwaehrung) {
		this.zielwaehrung = zielwaehrung;
	}
	public BigDecimal getMultiplikator() {
		return multiplikator;
	}
	public void setMultiplikator(BigDecimal multiplikator) {
		this.multiplikator = multiplikator;
	}
	public Kursqualitaet getKursqualitaet() {
		return kursqualitaet;
	}
	public void setKursqualitaet(Kursqualitaet kursqualitaet) {
		this.kursqualitaet = kursqualitaet;
	}
	
	

}
