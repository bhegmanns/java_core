package hegmanns.it.de.java6.corelanguage.exceptions;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Mit JAVA 7 duerfen Exception in der catch-Klausel nun auch zusammen gefasst
 * werden.
 * 
 * @author B. Hegmanns
 *
 */
public class ExceptionsZusammenfassen {

	/**
	 * Beispiel, wie die catch-Klauseln vor JAVA 7 aussehen mussten.
	 */
	@Test
	public void tryCatchAlt()
	{
		try{
			methodeMitIllegalArgumentException();
			methodeMitRuntimeException();
			methodeMitException();
			Assert.fail("");
		}catch(IllegalArgumentException e){
			
		}
		catch(RuntimeException e){
			Assert.fail("");
		}
		catch(IOException e){
			Assert.fail("");
		}
	}
	
	/**
	 * Mit JAVA 7 duerfen nun mehrere Exceptions in einer catch-Klausel zusammen gefasst
	 * werden.
	 * 
	 * Hinweis:
	 * Exceptions innerhalb der Vererbungshierarchy duerfen nicht in einer catch-Klausel
	 * enthalten sein. Beispielsweise darf in der catch-Klausel hier NICHT die
	 * IllegalArgumentException enthalten sein, weil IllegalArgumentException eine Unterklasse
	 * von RuntimeException ist.
	 */
	@Test
	public void tryCatchJava7()
	{
		try{
			methodeMitIllegalArgumentException();
			methodeMitRuntimeException();
			methodeMitException();
		}catch(RuntimeException | IOException e){
			MatcherAssert.assertThat(e, Matchers.instanceOf(IllegalArgumentException.class));
		}
	}
	
	private void methodeMitIllegalArgumentException() throws IllegalArgumentException
	{
		throw new IllegalArgumentException("eine IllegalArgumentException");
	}
	
	private void methodeMitRuntimeException() throws RuntimeException
	{
		throw new RuntimeException("eine RuntimeException");
	}
	
	private void methodeMitException() throws IOException
	{
		throw new IOException("eine IOException");
	}
}
