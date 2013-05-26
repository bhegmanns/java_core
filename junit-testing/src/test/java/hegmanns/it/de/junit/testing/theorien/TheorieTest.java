package hegmanns.it.de.junit.testing.theorien;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TheorieTest {

	@DataPoints public static int[] VALUES = {-1, 0, 1, 2, 3, 4};
	
	@Theory
	public void foo(int wert){
		Assume.assumeTrue(wert > 0);
		
		MatcherAssert.assertThat(wert, Matchers.greaterThanOrEqualTo(0));
	}
}
