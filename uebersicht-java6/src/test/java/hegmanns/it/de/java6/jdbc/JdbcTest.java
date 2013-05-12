package hegmanns.it.de.java6.jdbc;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class JdbcTest {

	@Test
	public void jdbcLifecycle() throws Exception
	{
		Datenbank db = new Datenbank("derbydb");
		db.tabelleErstellen();
		db.datenEinfuegen("hallo");
		String daten = db.leseDaten();
		
		MatcherAssert.assertThat(daten, Matchers.is("hallo"));
	}
}
