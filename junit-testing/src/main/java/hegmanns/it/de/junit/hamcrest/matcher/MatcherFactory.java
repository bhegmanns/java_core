package hegmanns.it.de.junit.hamcrest.matcher;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class MatcherFactory {

	public static <T> Matcher<T> und(Matcher<T> ...matchers){
		return Matchers.allOf(matchers);
	}
	
	public static <T> Matcher<T> oder(Matcher<T> ...matchers){
		return Matchers.anyOf(matchers);
	}
}
