package de.hegmanns.it.utils.core.wertebereich;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class WertebereichTest {

	private Wertebereich<Integer> wertebereichAllIncluded = Wertebereich.erstelleWertebereichIncluded(new Integer(0), new Integer(10));
	private Wertebereich<Integer> wertebereichAllExcluded = Wertebereich.erstelleWertebereichExcluded(new Integer(0), new Integer(10));
	private Wertebereich<Integer> wertebereichStartIncEndeEx = Wertebereich.erstelleWertebereichStartIncludedEndeExcluded(new Integer(0), new Integer(10));
	private Wertebereich<Integer> wertebereichStartExEndeInc = Wertebereich.erstelleWertebereichStartExcludedEndeIncluded(new Integer(0), new Integer(10));
	
	@Test
	public void grenzenEnthaltenAbfrageObergrenzeTrue()
	{
		MatcherAssert.assertThat(wertebereichAllIncluded.isEnthalten(10), Matchers.is(true));
	}
	
	@Test
	public void grenzenEnthaltenAbfrageUntergrenzeTrue()
	{
		MatcherAssert.assertThat(wertebereichAllIncluded.isEnthalten(0), Matchers.is(true));
	}
	
	@Test
	public void grenzenEnthaltenAbfrageUeberObergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichAllIncluded.isEnthalten(11), Matchers.is(false));
	}
	
	@Test
	public void grenzenEnthaltenAbfrageUnterUntergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichAllIncluded.isEnthalten(-1), Matchers.is(false));
	}
	
	@Test
	public void grenzenEnthaltenAbfrageZwischenGrenzenTrue()
	{
		MatcherAssert.assertThat(wertebereichAllIncluded.isEnthalten(5), Matchers.is(true));
	}
	
	
	
	//
	@Test
	public void grenzenNichtEnthaltenAbfrageObergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichAllExcluded.isEnthalten(10), Matchers.is(false));
	}
	
	@Test
	public void grenzenNichtEnthaltenAbfrageUntergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichAllExcluded.isEnthalten(0), Matchers.is(false));
	}
	
	@Test
	public void grenzenNichtEnthaltenAbfrageUeberObergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichAllExcluded.isEnthalten(11), Matchers.is(false));
	}
	
	@Test
	public void grenzenNichtEnthaltenAbfrageUnterUntergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichAllExcluded.isEnthalten(-1), Matchers.is(false));
	}
	
	@Test
	public void grenzenNichtEnthaltenAbfrageZwischenGrenzenTrue()
	{
		MatcherAssert.assertThat(wertebereichAllExcluded.isEnthalten(5), Matchers.is(true));
	}
	

	//
	@Test
	public void obergrenzeEnthaltenUntergrenzeNichtEnthaltenAbfrageObergrenzeTrue()
	{
		MatcherAssert.assertThat(wertebereichStartExEndeInc.isEnthalten(10), Matchers.is(true));
	}
	
	@Test
	public void obergrenzeEnthaltenUntergrenzeNichtEnthaltenAbfrageUntergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichStartExEndeInc.isEnthalten(0), Matchers.is(false));
	}
	
	@Test
	public void obergrenzeEnthaltenUntergrenzeNichtEnthaltenAbfrageUeberObergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichStartExEndeInc.isEnthalten(11), Matchers.is(false));
	}
	
	@Test
	public void obergrenzeEnthaltenUntergrenzeNichtEnthaltenAbfrageUnterUntergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichStartExEndeInc.isEnthalten(-1), Matchers.is(false));
	}
	
	@Test
	public void obergrenzeEnthaltenUntergrenzeNichtEnthaltenAbfrageZwischenGrenzenTrue()
	{
		MatcherAssert.assertThat(wertebereichStartExEndeInc.isEnthalten(5), Matchers.is(true));
	}

	
	//
	@Test
	public void obergrenzeNichtEnthaltenUntergrenzeEnthaltenAbfrageObergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichStartIncEndeEx.isEnthalten(10), Matchers.is(false));
	}
	
	@Test
	public void obergrenzeNichtEnthaltenUntergrenzeEnthaltenAbfrageUntergrenzeTrue()
	{
		MatcherAssert.assertThat(wertebereichStartIncEndeEx.isEnthalten(0), Matchers.is(true));
	}
	
	@Test
	public void obergrenzeNichtEnthaltenUntergrenzeEnthaltenAbfrageUeberObergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichStartIncEndeEx.isEnthalten(11), Matchers.is(false));
	}
	
	@Test
	public void obergrenzeNichtEnthaltenUntergrenzeEnthaltenAbfrageUnterUntergrenzeFalse()
	{
		MatcherAssert.assertThat(wertebereichStartIncEndeEx.isEnthalten(-1), Matchers.is(false));
	}
	
	@Test
	public void obergrenzeNichtEnthaltenUntergrenzeEnthaltenAbfrageZwischenGrenzenTrue()
	{
		MatcherAssert.assertThat(wertebereichStartIncEndeEx.isEnthalten(5), Matchers.is(true));
	}
}
