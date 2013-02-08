package hegmanns.de.de.hegmanns.puzzles;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class EinfacherMockitoTest {

	@Test
	public void einErsterTest(){
		StringBuffer sb = new StringBuffer();
		sb.append("Hier").append(" ist").append(" Joe.");
		String s = "Hier ist Joe.";
		
		MatcherAssert.assertThat(sb.toString(), Matchers.is(s));
		MatcherAssert.assertThat(sb.toString(), Matchers.not(Matchers.sameInstance(s)));
	}
	
	public void einZweiterTest(){
		
	}

}
