package hegmanns.it.de.junit.testing.theorien;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * 
 * @author B. Hegmanns
 */
@RunWith(Theories.class)
public class EinTestMitTheories {

	@DataPoints public static int[] VALUES = {-1, 0, 1, 2, 3, 4};
	
	private static String getesteteZahlen = "";
	
	@Theory
	public void foo(int wert){
		Assume.assumeTrue(wert > 0);
		System.out.println("" + wert);
		MatcherAssert.assertThat(wert, Matchers.greaterThanOrEqualTo(0));
		getesteteZahlen += wert + " ";
	}
	
	@AfterClass
	public static void schlusstest(){
		MatcherAssert.assertThat(getesteteZahlen.trim(), Matchers.is("1 2 3 4"));
	}
	
}
