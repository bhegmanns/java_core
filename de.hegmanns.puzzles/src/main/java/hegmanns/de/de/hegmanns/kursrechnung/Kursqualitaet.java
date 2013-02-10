package hegmanns.de.de.hegmanns.kursrechnung;

/**
 * Beschreibt die Herkunft des Kurses,
 * ist mit der Aktualitaet des Kurses vergleichbar.
 * 
 * 
 * @author B. Hegmanns
 */
public enum Kursqualitaet {
	/** Kurs aus der Datenbank,
	 * entspricht die "schlechteste" Qualitaet.*/
	DATENBANK,
	
	/**Kurs entspricht dem letzten bekannten Tageskurs,
	 * im Allgemeinen dem Vortageskurs*/
	TAGESKURS,
	
	/** Kurs entspricht dem Neartime-Kurs, im Allgemeinen
	 * eine Life-Abfrage, allerdings kann der aktuelle
	 * Kurs bereits geaendert sein.*/
	NEARTIME, 
	
	/** Kust entspricht dem aktuellen Wert.*/
	REALTIME;
}
