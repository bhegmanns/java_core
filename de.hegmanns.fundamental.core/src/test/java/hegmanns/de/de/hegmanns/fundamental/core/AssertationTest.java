package hegmanns.de.de.hegmanns.fundamental.core;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class AssertationTest {

	@Test
	public void assertNullGibtKeineException(){
		Object o = null;
		Assertation.isNull(o, "Instanz soll null sein");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void assertNullGibtException(){
		Object o = new Object();
		Assertation.isNull(o, "Instanz soll null sein");
	}
	
	@Test
	public void assertNotNullGibtKeineException(){
		Object o = new Object();
		Assertation.notNull(o, "Intanz ist null");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void assertNotNullGibtException(){
		Object o = null;
		Assertation.notNull(o, "Intanz ist null");
	}
	
	@Test
	public void assertLengthKorrekt(){
		String text = "EinText";
		Assertation.lengthBetween(text, false, "", text.length());
	}
	
	public void assertLengthMitTrimKorrekt(){
		String text = "EinText";
		int originaleTextlaenge = text.length();
		String preAndPostfix = StringUtils.repeat(" ", Integer.MAX_VALUE);
		text = preAndPostfix + text + preAndPostfix;
		
		Assertation.lengthBetween(text, true, "", originaleTextlaenge);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void assertLengthMitNullErzeugtException()
	{
		String text = null;
		Assertation.lengthBetween(text, true, "", 1);
	}
	
	@Test(expected = RuntimeException.class)
	public void assertLengthMitNegativerLaengeErzeugtException(){
		String text = "EinText";
		Assertation.lengthBetween(text, false, "", -1);
	}
	
	@Test(expected = RuntimeException.class)
	public void assertLengthMitMaxLengthKleinerMinLengthErzeugtException(){
		String text = "EinText";
		Assertation.lengthBetween(text, false, "", 5, 1);
	}
	
	

}
