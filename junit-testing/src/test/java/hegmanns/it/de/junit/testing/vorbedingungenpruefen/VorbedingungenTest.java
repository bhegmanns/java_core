package hegmanns.it.de.junit.testing.vorbedingungenpruefen;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.Before;
import org.junit.internal.AssumptionViolatedException;

/**
 * Diese Klasse verwendet eine Bedingung zur Entscheidung der Testdurchfuehrung
 * bzw. Testvorsetzung:
 * <code>
 * 	Assume.assumeTrue(...)
 * </code>
 * Es gibt noch weitere Methoden an der Assume-Klasse, die ggf. die weitere
 * Testausfuehrung in der Methode stoppen koennen.
 * 
 * Die Assume-Methode fuehren zu einer {@link AssumptionViolatedException}, die dann
 * aber vom TestRunner abgefangen wird.
 * 
 * @author B. Hegmanns
 *
 */
public class VorbedingungenTest {

	private static boolean ausfuehren = true;
	
	@Before
	public void vorJedemTest(){
		ausfuehren = !ausfuehren;
	}
	
	@Test
	public void testmethode1(){
		Assume.assumeTrue("Test wird nicht ausgefuehrt", ausfuehren);
		MatcherAssert.assertThat(ausfuehren, Matchers.is(true));
	}
	
	@Test
	public void testmethode2(){
		Assume.assumeTrue("Test wird nicht ausgefuehrt", ausfuehren);
		MatcherAssert.assertThat(ausfuehren, Matchers.is(true));
	}
	
	@Test
	public void testmethode3(){
		Assume.assumeTrue("Test wird nicht ausgefuehrt", ausfuehren);
		MatcherAssert.assertThat(ausfuehren, Matchers.is(true));
	}
	
	@Test
	public void testmethode4(){
		Assume.assumeTrue("Test wird nicht ausgefuehrt", ausfuehren);
		MatcherAssert.assertThat(ausfuehren, Matchers.is(true));
	}
	
	/**
	 * Hier sieht man, dass die assume-Methode auch mitten in der Testmethode
	 * aufgerufen werden darf.
	 */
	@Test
	public void pruefungNachTestbeginn(){
		int a = 5;
		try{
			a = 10;
			Assume.assumeTrue(a < 10);
			Assert.fail("Hier darf es nicht mehr weiter gehen.");
		}catch(Exception e){
			MatcherAssert.assertThat("Andere Klasse erwartet.", e.getClass().getName(), Matchers.is(AssumptionViolatedException.class.getName()));
			throw e;
		}
		}
	
	
}
