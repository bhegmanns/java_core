package hegmanns.it.de.java6.nio;

import java.io.File;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * 
 * @author B. Hegmanns
 *
 */
public class UmgangMitVerzeichnisbaeumenTest {

	@Test
	public void dateienUndAnlegenAlt()
	{
		Dateisystem dateisystem = new DateisystemAlt(new File("."), "meintest");
		dateisystem.verzeichnisseAnlegen("a", "b", "c");
		String struktur = dateisystem.verzeichnisAusgeben();
		System.out.println("" + struktur);
		MatcherAssert.assertThat(dateisystem.jedesVerzeichnisBefuellen("willi.txt"), Matchers.is(3));
		struktur = dateisystem.verzeichnisAusgeben();
		System.out.println("" + struktur);
		dateisystem.verzeichnisLoeschen();
		
		MatcherAssert.assertThat(dateisystem.verzeichnisAusgeben(), Matchers.is(""));
	}
	
	public void dateienUndAnlegenNeu()
	{
		
	}
}
