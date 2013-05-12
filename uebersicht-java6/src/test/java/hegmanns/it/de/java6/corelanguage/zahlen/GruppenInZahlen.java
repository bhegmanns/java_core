package hegmanns.it.de.java6.corelanguage.zahlen;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * In Zahlen koennen (unabhaengig vom Format) beliebig viele Unterstriche ZWISCHEN
 * Ziffern gesetzt werden.
 * 
 * Wichtig:
 * Spezifizierer des jeweiligen Zahlenformats (0x fuer HEX, 0b fuer BIN) duerfen hierbei
 * NICHT auseinander gerissen werden!
 * 
 * @author B. Hegmanns
 */
public class GruppenInZahlen {
	
	int kleineVergleichszahl = 1000;
	long vergleichszahl = 100000000L;
	double doublezahl = 555.5555;
	float floatzahl = 555.5555F;

	/**
	 * Zwischen zwei Ziffern einer Zahl koennen Unterstriche
	 * (beispielsweise zu Gruppierungszwecken) gesetzt werden.
	 */
	@Test
	public void gleichheitMitUnterstrich()
	{
		long a1 = 100_00_00_00L;
		
		MatcherAssert.assertThat(a1, Matchers.is(vergleichszahl));
	}
	
	/**
	 * Die Anzahl der Unterstriche ist egal.
	 */
	@Test
	public void vieleUnterstricheSindMoeglich()
	{
		long a1 = 100_______0000____00L;
		MatcherAssert.assertThat(a1, Matchers.is(vergleichszahl));
		
	}
	
	@Test
	public void unterstricheInDezimalbruechenErlaubt()
	{
		double a = 55__5.55_5__5;
		MatcherAssert.assertThat(a, Matchers.is(doublezahl));
	}
	
	/**
	 * Der/die Unterstriche muessen ZWISCHEN zwei Ziffern stehen.
	 * Folglich darf ein Unterstrich NICHT um Anfang oder am Ende des
	 * Zahlen-Literals stehen.
	 * Auch direkt vor einem Typ-Spezifizierer (L, l, d, D) darf
	 * kein Unterstrich stehen.
	 * Der auskommentierte Code wuerde daher auf einen Fehler laufen.
	 * (Underscores have to be located within digits)
	 * 
	 * Fuer fuehrende Unterstriche siehe {@link #seltsameFehlermeldungenMitUnterstrichen()}
	 */
	@Test
	public void unterstrichDarfNurAmEndeStehen()
	{
		int a = 0;
//		a = 1000_;
		
		long b = 1000000L;
//		b = 1000000_L;
	}
	
	/**
	 * Wie oben, Unterstriche muessen ZWISCHEN zwei Ziffern stehen.
	 * Daher darf ein Unterstrich auch NICHT direkt vor oder nach einem
	 * Dezimaltrennzeichen stehen.
	 */
	@Test
	public void unterstrichForOderNachKommaGehtNicht()
	{
		double a = 0;
//		a = 555_.55;
//		a = 555._55;
	}
	
	/**
	 * Der auskommentierte Code fuehrt erstaunlicherweise NICHT auf einen Fehler wegen
	 * der unerlaubten Position des Unterstrichs, sondern fuehrt auf einen Fehler
	 * wegen nicht definierter Variable "_1234".
	 * Das liegt daran, weil eine Variable erlaubterweise entweder mit einem Buchstaben oder mit einem
	 * Unterstrich beginnen darf.
	 * Da allerdingt der Variablen kein Wert zugewiesen ist, gibt es die Fehlermeldung:
	 * (_1234 cannot be resolved to a variable)
	 */
	@Test
	public void seltsameFehlermeldungenMitUnterstrichen()
	{
		int a = 0;
		
		// geht nicht, mit _1234 wuerde eine Variable definiert werden
//		a = _1234;
	}
	
	/**
	 * Wie schon in {@link #seltsameFehlermeldungenMitUnterstrichen()} beschrieben, wird
	 * durch eine Zahl mit fuehrenden Unterstrich eine Variable definiert. Das kann zu tollen
	 * Stilblueten fuehren und ist besser zu unterlassen.
	 */
	@Test
	public void achtungPseudoZahl()
	{
		int _1234 = 2468;
		int a = 1 + _1234;
		
		MatcherAssert.assertThat(a, Matchers.is(2469));
	}
}
