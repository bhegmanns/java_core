package hegmanns.it.de.junit.testing.hamcrest;

import hegmanns.it.de.junit.hamcrest.matcher.RegexMatcher;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author B. Hegmanns
 */
public class EinfacheVergleicheMitHamcrestTest {

	/**
	 * 
	 */
	@Test
	public void zeichenkette()
	{
		String a = "Java";
		String b = "java";
		String c = "Java";
		
		MatcherAssert.assertThat("", a, Matchers.is(c));
//		MatcherAssert.assertThat("", b, Matchers.is)
	}
	
	@Test
	public void behandlungMitNullCollection(){
		List<String> liste = null;
		
		liste = Arrays.asList(new String[]{"a", "b", "c"});
		MatcherAssert.assertThat("", liste, Matchers.contains("a", "b", "c"));
		MatcherAssert.assertThat("", liste, Matchers.containsInAnyOrder("b", "a", "c"));
		MatcherAssert.assertThat("", liste, Matchers.hasItems("b", "c"));
		
		liste = Arrays.asList(new String[]{"a", "b", "c", "d"});
		MatcherAssert.assertThat("", liste, Matchers.contains("a", "b", "c"));
		
		liste = null;
		
		try{
		MatcherAssert.assertThat("", liste, Matchers.contains("a", "b", "c"));
		Assert.fail("Habe AssertionError erwartet.");
		}catch(AssertionError e){
			System.out.println("" + e.getMessage());
			MatcherAssert.assertThat("Message '" + e.getMessage() + "' matched nicht auf regulaeren Ausdruck.", e.getMessage(), RegexMatcher.findWithRegex("iterable containing.*a.*b.*c.*was null"));
		}
		
		
	}
}
