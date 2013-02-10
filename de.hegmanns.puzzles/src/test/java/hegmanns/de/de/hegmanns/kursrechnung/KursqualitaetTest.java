package hegmanns.de.de.hegmanns.kursrechnung;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class KursqualitaetTest {

	@Test
	public void KursQualitaetenEqualsFunktioniert(){
		MatcherAssert.assertThat(true, Matchers.is(Kursqualitaet.REALTIME.equals(Kursqualitaet.REALTIME)));
		MatcherAssert.assertThat(false, Matchers.is(Kursqualitaet.REALTIME.equals(Kursqualitaet.DATENBANK)));
	}
}
