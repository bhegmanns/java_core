package hegmanns.de.de.hegmanns.kursrechnung;

import java.math.BigDecimal;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class GeldbetragTest {
	

	private Geldbetrag geldbetragWaehrungA1 = new Geldbetrag(BigDecimal.ONE, new Waehrung("AAA"));
	private Geldbetrag geldbetragWaehrungA10 = new Geldbetrag(BigDecimal.TEN, new Waehrung("AAA"));
	
	private Geldbetrag geldbetragWaehrungB1 = new Geldbetrag(BigDecimal.ONE, new Waehrung("BBB"));
	
	@Rule
	public ExpectedException fehler = ExpectedException.none();

	@Test
	public void geldbetraegeAddierenBeiGleicherWaehrung()
	{
		Geldbetrag summe = geldbetragWaehrungA1.add(geldbetragWaehrungA10);
		MatcherAssert.assertThat(summe, Matchers.notNullValue());
		MatcherAssert.assertThat(summe, Matchers.is(new Geldbetrag(BigDecimal.valueOf(11), new Waehrung("AAA"))));
	}
	
	@Test
	public void geldbetraegeSubtrahierenBeiGleicherWaehrung()
	{
		Geldbetrag summe = geldbetragWaehrungA10.subtract(geldbetragWaehrungA1);
		MatcherAssert.assertThat(summe, Matchers.notNullValue());
		MatcherAssert.assertThat(summe, Matchers.is(new Geldbetrag(BigDecimal.valueOf(9), new Waehrung("AAA"))));
	}
	
	@Test
	public void geldbetraegeMultiplizierenBeiGleicherWaehrung()
	{
		Geldbetrag summe = geldbetragWaehrungA10.multiply(geldbetragWaehrungA1);
		MatcherAssert.assertThat(summe, Matchers.notNullValue());
		MatcherAssert.assertThat(summe, Matchers.is(new Geldbetrag(BigDecimal.TEN, new Waehrung("AAA"))));
	}
	
	@Test
	public void geldbetraegeDividierenBeiGleicherWaehrung()
	{
		Geldbetrag summe = geldbetragWaehrungA10.divide(geldbetragWaehrungA1);
		MatcherAssert.assertThat(summe, Matchers.notNullValue());
		MatcherAssert.assertThat(summe, Matchers.is(new Geldbetrag(BigDecimal.TEN, new Waehrung("AAA"))));
	}
	
	@Test
	public void geldbetraegeAddierenBeiUngleicherWaehrung(){
		fehler.expect(IllegalArgumentException.class);
		fehler.expectMessage(Matchers.containsString("ungleich Summand-Waehrung"));
		
		geldbetragWaehrungA1.add(geldbetragWaehrungB1);
	}
	
	@Test
	public void geldbetraegeMultiplizierenBeiUngleicherWaehrung(){
		fehler.expect(IllegalArgumentException.class);
		fehler.expectMessage(Matchers.containsString("ungleich Faktor-Waehrung"));
		
		geldbetragWaehrungA1.multiply(geldbetragWaehrungB1);
	}

	@Test
	public void geldbetraegeDividierenBeiUngleicherWaehrung(){
		fehler.expect(IllegalArgumentException.class);
		fehler.expectMessage(Matchers.containsString("ungleich Divisor-Waehrung"));
		
		geldbetragWaehrungA1.divide(geldbetragWaehrungB1);
	}
	
	@Test
	public void geldbetraegeSubtrahentBeiUngleicherWaehrung(){
		fehler.expect(IllegalArgumentException.class);
		fehler.expectMessage(Matchers.containsString("ungleich Subtrahent-Waehrung"));
		
		geldbetragWaehrungA1.subtract(geldbetragWaehrungB1);
	}
	
	public void geldbetraegeAddierenMitBigDezimal(){
		Geldbetrag geldbetrag = geldbetragWaehrungA1.add(BigDecimal.valueOf(9));
		MatcherAssert.assertThat(geldbetrag, Matchers.is(new Geldbetrag(BigDecimal.TEN, geldbetragWaehrungA1.getWaehrung())));
	}
	
	public void geldbetraegeSubtrahierenMitBigDezimal(){
		Geldbetrag geldbetrag = geldbetragWaehrungA10.subtract(BigDecimal.valueOf(9));
		MatcherAssert.assertThat(geldbetrag, Matchers.is(new Geldbetrag(BigDecimal.ONE, geldbetragWaehrungA1.getWaehrung())));
	}
	
	public void geldbetraegeMultiplizierenMitBigDezimal(){
		Geldbetrag geldbetrag = geldbetragWaehrungA1.add(BigDecimal.TEN);
		MatcherAssert.assertThat(geldbetrag, Matchers.is(new Geldbetrag(BigDecimal.TEN, geldbetragWaehrungA1.getWaehrung())));
	}
	
	public void geldbetraegeDividierenitBigDezimal(){
		Geldbetrag geldbetrag = geldbetragWaehrungA10.divide(BigDecimal.ONE);
		MatcherAssert.assertThat(geldbetrag, Matchers.is(new Geldbetrag(BigDecimal.TEN, geldbetragWaehrungA1.getWaehrung())));
	}
}
