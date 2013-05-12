package hegmanns.it.de.java6.corelanguage.switchmitstring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Mit Java7 duerfen auch Strings in switch-case-Anweisungen ausgewertet werden.
 * Dies war bisher nur mit einer Pseudo-Switch-Anweisung, bestehend aus vielen
 * If-Else-Cascaden moeglich.
 * Die Bedingung, dass in den Case-Zweigen als Argumente Konstanten stehen muessen,
 * bleibt erhalten.
 * Der Vergleich ist case-sensitiv und beruht auf die Ausfuehrung der String.equals-Methode.
 * 
 * WICHTIG:
 * In die switch-Anweisungen koennen ausschliesslich
 * ganze Zahlen, Enums und Strings. Keine weiteren Typen.
 * 
 * @author B. Hegmanns
 *
 */
public class StringInSwitch {

	@Test
	public void stringCase()
	{
		Calendar zeit = Calendar.getInstance();
		zeit.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		
		DateFormat df = new SimpleDateFormat("EEEE"); // Tag ausgeschrieben
		
		String tagname = df.format(zeit.getTime());
		
		boolean weekend = false;
		switch(tagname)
		{
		case "Montag":
		case "Dienstag":
		case "Mittwoch":
		case "Donnerstag":
		case "Freitag":
			weekend = false;
			break;
		case "Samstag":
		case "Sonntag": 
			weekend = true;
			break;
		};
		
		MatcherAssert.assertThat(weekend, Matchers.is(true));
	}
}
