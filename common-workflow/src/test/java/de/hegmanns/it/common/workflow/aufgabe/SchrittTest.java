package de.hegmanns.it.common.workflow.aufgabe;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.runners.MockitoJUnitRunner;

import de.hegmanns.it.common.workflow.aufgabe.cache.Cache;
import de.hegmanns.it.common.workflow.aufgabe.cache.TestCacheKonstanten;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SchrittTest {
	
	@Spy
	public Cache cache;
	
	
	@Test
	public void testSchrittAusfuehrenCacheKonstantenWerdenGelesen(){
		TestSchritt testSchritt = new TestSchritt(BigDecimal.ONE);
		testSchritt.schrittAktionAusfuehren(cache, null);
		
		verify(cache).getFromCache(eq(TestCacheKonstanten.A));
		verify(cache).getFromCache(TestCacheKonstanten.B);
	}
	@Test
	public void testSchrittAusfuehrenCacheKonstanteCWirdNichtGelesen(){
		TestSchritt testSchritt = new TestSchritt(BigDecimal.ONE);
		testSchritt.schrittAktionAusfuehren(cache, null);
		
		
		verify(cache, VerificationModeFactory.times(0)).getFromCache(eq(TestCacheKonstanten.C));
	}
	@Test
	public void testSchrittAusfuehrenCacheKonstanteCWirdGeschrieben(){
		TestSchritt testSchritt = new TestSchritt(BigDecimal.ONE);
		testSchritt.schrittAktionAusfuehren(cache, null);
		
		verify(cache).setIntoCache(eq(TestCacheKonstanten.C), eq(BigDecimal.ONE));
	}
	
	@Test
	public void testSchrittAusfuehrenCacheKonstanteCEnthaeltErwartetenWert(){
		TestSchritt testSchritt = new TestSchritt(BigDecimal.ONE);
		testSchritt.schrittAktionAusfuehren(cache, null);
		
		assertThat(cache.getFromCache(TestCacheKonstanten.C), equalTo(BigDecimal.ONE));
	}
}
