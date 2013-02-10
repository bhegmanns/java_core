package hegmanns.de.de.hegmanns.puzzles.ungeradezahlen;

public class UngeradeZahlIndizierenMitBitoperationTest extends
		UngeradeZahlIndizierenTest {

	@Override
	protected boolean callToIndikator(Long zahl) {
		return UngeradeZahlIndikator.istUngeradeBitUndZero(zahl);
	}

}
