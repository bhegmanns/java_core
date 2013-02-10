package hegmanns.de.de.hegmanns.puzzles.ungeradezahlen;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Testklasse fuer die Indikatormethoden.
 * 
 * @author B. Hegmanns
 */
public abstract class UngeradeZahlIndizierenTest {

	protected abstract boolean callToIndikator(Long zahl);
	
	@Test
	public void grossePositiveLongZahlUngerade(){
		MatcherAssert.assertThat("Zahl '" + Long.MAX_VALUE + "' ist ungerade!", callToIndikator(Long.MAX_VALUE), Matchers.is(true));
	}
	
	@Test
	public void grossePositiveLongZahlGerade(){
		MatcherAssert.assertThat("Zahl '" + (Long.MAX_VALUE - 1) + "' ist gerade!", callToIndikator(Long.MAX_VALUE - 1), Matchers.is(false));
	}
	
	@Test
	public void ermittleFuerZero(){
		MatcherAssert.assertThat("0 ist gerade!!!", callToIndikator(0L), Matchers.is(false));
	}
	
	@Test
	public void ermittleNegativeZahlGerade(){
		MatcherAssert.assertThat("Zahl '" + Long.MIN_VALUE + "' ist gerade!!!", callToIndikator(Long.MIN_VALUE), Matchers.is(false));
	}
	
	public void ermittleNegativeZahlUngerade(){
		MatcherAssert.assertThat("Zahl '" + (Long.MIN_VALUE + 1) + "' ist ungerade!!!", callToIndikator(Long.MIN_VALUE + 1), Matchers.is(true));
	}
	
	
}
