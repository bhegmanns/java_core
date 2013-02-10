package hegmanns.de.de.hegmanns.puzzles.ungeradezahlen;

public class UngeradeZahlIndizierenMitModuloDivisionTest extends
		UngeradeZahlIndizierenTest {

	@Override
	protected boolean callToIndikator(Long zahl) {
		return UngeradeZahlIndikator.istUngeradeMitModulo(zahl);
	}

}
