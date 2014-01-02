package de.hegmanns.it.utils.core.wertebereich;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class WertebereichValueTest {

	@Test
	public void wertEnthaltenVergleichGleicherWertLowerThanErgibtFalse()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat(integerValue.isLowerThan(new Integer(10)), Matchers.is(false));
	}
	
	@Test
	public void wertEnthaltenVergleichKleinerWertLowerThanErgibtFalse()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat(integerValue.isLowerThan(5), Matchers.is(false));
	}
	
	@Test
	public void wertEnthaltenVergleichGroesserWertLowerThanErgibtTrue()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat(integerValue.isLowerThan(11), Matchers.is(true));
	}
	
	
	
	
	@Test
	public void wertEnthaltenVergleichGleicherWertGreaterThanErgibtFalse()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat(integerValue.isGreaterThan(10), Matchers.is(false));
	}
	
	@Test
	public void wertEnthaltenVergleichKleinerWertGreaterThanErgibtTrue()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat("" + integerValue + ".isGreaterThan(5) >>> " + integerValue.isGreaterThan(5), integerValue.isGreaterThan(new Integer(5)), Matchers.is(true));
	}
	
	@Test
	public void wertEnthaltenVergleichGroesserWertGreaterThanErgibtFalse()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat(integerValue.isGreaterThan(11), Matchers.is(false));
	}
	
	
	
	
	
	@Test
	public void wertEnthaltenVergleichGleicherWertEqualsThanErgibtTrue()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat(integerValue.isEqualsWith(10), Matchers.is(true));
	}
	
	@Test
	public void wertEnthaltenVergleichKleinerWertEqualsThanErgibtFalse()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat("" + integerValue + ".isGreaterThan(5) >>> " + integerValue.isGreaterThan(5), integerValue.isEqualsWith(new Integer(5)), Matchers.is(false));
	}
	
	@Test
	public void wertEnthaltenVergleichGroesserWertEqualsThanErgibtFalse()
	{
		WertebereichValue<Integer> integerValue = new WertebereichValue<Integer>(10);
		
		MatcherAssert.assertThat(integerValue.isEqualsWith(11), Matchers.is(false));
	}
	
	
}
