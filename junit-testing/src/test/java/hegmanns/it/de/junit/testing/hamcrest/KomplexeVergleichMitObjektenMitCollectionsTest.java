package hegmanns.it.de.junit.testing.hamcrest;

import hegmanns.it.de.junit.basisklassen.Konto;
import hegmanns.it.de.junit.basisklassen.Kontoart;
import hegmanns.it.de.junit.hamcrest.matcher.MatcherFactory;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class KomplexeVergleichMitObjektenMitCollectionsTest {

	private List<Konto> konten = null;
	
	@Before
	public void init(){
		konten = new ArrayList<>();
		
		konten.add(new Konto("11111", Kontoart.GIROKONTO, BigDecimal.TEN, BigDecimal.TEN));
		konten.add(new Konto("11112", Kontoart.SPARKONTO, BigDecimal.valueOf(1000), null));
		konten.add(new Konto("11113", Kontoart.SPARKONTO, BigDecimal.valueOf(100), null));
		konten.add(new Konto("11114", Kontoart.TAGEGELDKONTO, BigDecimal.valueOf(10000), null));
	}
	
	@Test
	public void alleKontenhabenKontonummer(){
		
		MatcherAssert.assertThat("",konten, Matchers.everyItem(Matchers.<Konto>hasProperty("kontonummer")));
	}
	
	@Test
	public void allgemeineEigenschaftenDerElemente(){
		MatcherAssert.assertThat("", konten,
				Matchers.everyItem(Matchers.<Konto>allOf(
						Matchers.hasProperty("kontonummer", Matchers.startsWith("1111"))
						, Matchers.hasProperty("saldo", Matchers.greaterThan(BigDecimal.ZERO))
						)));
	}
	
	@Test
	public void mindestensEinGirokonto(){
		MatcherAssert.assertThat("", konten,
				Matchers.hasItem(Matchers.<Konto>hasProperty("kontoart", Matchers.is(Kontoart.GIROKONTO))));
	}
	
	@Test
	public void nurGirokontenHabenKreditlinie(){
//		MatcherAssert.assertThat("", konten, Matchers.everyItem(girokontoHatKreditlinieUngleichNull()));
//		MatcherAssert.assertThat("", konten,
//				Matchers.hasItem(itemMatcher)
	}
	
//	oder(und(Matchers.hasProperty("kontoart", Matchers.not(Kontoart.GIROKONTO)), 
//			Matchers.hasProperty("kreditlinie", Matchers.nullValue())), und(Matchers.hasProperty("kontoart", Matchers.is(Kontoart.GIROKONTO)),
//			Matchers.hasProperty("kreditlinie", Matchers.notNullValue())))
	
	private <Konto> Matcher<Konto> girokontoHatKreditlinieUngleichNull(){
		return null;
	}
}
