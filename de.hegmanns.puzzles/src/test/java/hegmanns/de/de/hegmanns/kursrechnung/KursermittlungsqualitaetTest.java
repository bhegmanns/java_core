package hegmanns.de.de.hegmanns.kursrechnung;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class KursermittlungsqualitaetTest {

	@Test
	public void KursermittlungsQualitaetEqualsFunktioniert(){
		Kursermittlungsqualitaet qualitaet1 = new Kursermittlungsqualitaet(Kursqualitaet.REALTIME);
		Kursermittlungsqualitaet qualitaet2 = new Kursermittlungsqualitaet(Kursqualitaet.REALTIME);
		
		Kursermittlungsqualitaet qualitaet3 = new Kursermittlungsqualitaet(Kursqualitaet.DATENBANK);
		
		MatcherAssert.assertThat(qualitaet1, Matchers.is(qualitaet2));
		MatcherAssert.assertThat(qualitaet1, Matchers.not(Matchers.is(qualitaet3)));
		
		qualitaet1.setMoeglicheMindestQualitaet(Kursqualitaet.NEARTIME);
		qualitaet2.setMoeglicheMindestQualitaet(Kursqualitaet.REALTIME);
		MatcherAssert.assertThat(qualitaet1, Matchers.not(Matchers.is(qualitaet2)));
		
		qualitaet1.setMoeglicheMindestQualitaet(Kursqualitaet.REALTIME);
		MatcherAssert.assertThat(qualitaet1, Matchers.is(qualitaet2));
	}
}
