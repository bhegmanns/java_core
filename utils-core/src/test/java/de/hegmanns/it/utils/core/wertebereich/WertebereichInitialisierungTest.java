package de.hegmanns.it.utils.core.wertebereich;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class WertebereichInitialisierungTest {

	
	@Test
	public void wertebereichGrenzenIncluded()
	{
		Wertebereich<Integer> wertebereich = Wertebereich.erstelleWertebereichIncluded(0, 10);
		
		MatcherAssert.assertThat(wertebereich.isEndewertEnthalten(), Matchers.is(true));
		MatcherAssert.assertThat(wertebereich.isStartwertEnthalten(), Matchers.is(true));
	}
	
	@Test
	public void wertebereichGrenzenExcluded()
	{
		Wertebereich<Integer> wertebereich = Wertebereich.erstelleWertebereichExcluded(0, 10);
		
		MatcherAssert.assertThat(wertebereich.isEndewertEnthalten(), Matchers.is(false));
		MatcherAssert.assertThat(wertebereich.isStartwertEnthalten(), Matchers.is(false));
	}
	
	@Test
	public void wertebereichUntergrenzeIncludedObergrenzeExcluded()
	{
		Wertebereich<Integer> wertebereich = Wertebereich.erstelleWertebereichStartIncludedEndeExcluded(0, 10);
		
		MatcherAssert.assertThat(wertebereich.isEndewertEnthalten(), Matchers.is(false));
		MatcherAssert.assertThat(wertebereich.isStartwertEnthalten(), Matchers.is(true));
	}
	
	@Test
	public void wertebereichUntergrenzeExcludedObergrenzeIncluded()
	{
		Wertebereich<Integer> wertebereich = Wertebereich.erstelleWertebereichStartExcludedEndeIncluded(0, 10);
		
		MatcherAssert.assertThat(wertebereich.isEndewertEnthalten(), Matchers.is(true));
		MatcherAssert.assertThat(wertebereich.isStartwertEnthalten(), Matchers.is(false));
	}
}
