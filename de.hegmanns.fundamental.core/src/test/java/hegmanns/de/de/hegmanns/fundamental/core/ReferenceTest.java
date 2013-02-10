package hegmanns.de.de.hegmanns.fundamental.core;

import java.math.BigDecimal;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Testfaelle fuer {@link Ref}.
 * 
 * @author B. Hegmanns
 */
public class ReferenceTest {

	/**
	 * 
	 */
	@Test
	public void simpleReferenceTest(){
		Ref<BigDecimal> meinValue = new Ref<BigDecimal>();
		
		meinValue.set(new BigDecimal(123));
		
		MatcherAssert.assertThat(meinValue, Matchers.notNullValue());
		MatcherAssert.assertThat(meinValue.get(), Matchers.is(new BigDecimal(123)));
	}

}
