package de.hegmanns.it.common.workflow.aufgabe;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import de.hegmanns.it.common.workflow.aufgabe.cache.CacheKonstante;
import de.hegmanns.it.common.workflow.aufgabe.cache.TestCacheKonstanten;

public class AufgabeTest {

	@SuppressWarnings("unchecked")
	@Test
	public void gibtRequestedParameterZurueck(){
		
		Schritt[] schritte = new Schritt[]{new TestErstSchritt(), new TestSchritt(BigDecimal.ONE)};
		
		Aufgabe aufgabe = new Aufgabe(Arrays.asList(schritte));
		
		Set<CacheKonstante<?>> requestedParameter = aufgabe.getRequestedParameter();
		
		assertThat(requestedParameter, hasSize(1));

		assertThat((CacheKonstante<Integer>) requestedParameter.iterator().next(), equalTo(TestCacheKonstanten.B));
	}
}
