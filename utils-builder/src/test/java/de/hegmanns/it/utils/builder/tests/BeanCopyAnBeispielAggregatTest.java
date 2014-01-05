package de.hegmanns.it.utils.builder.tests;

import java.lang.reflect.InvocationTargetException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import de.hegmanns.it.utils.builder.TestdatenBuilderBeispielAggregat;
import de.hegmanns.it.utils.builder.BuilderException;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielAggregat;





public class BeanCopyAnBeispielAggregatTest {


	
	
	@Test
	public void kopieWirdErstellt() throws IllegalAccessException, InvocationTargetException,  BuilderException
	{
		BeispielAggregat aggregat =
				TestdatenBuilderBeispielAggregat.get()
				.name("Willi")
				.zeitpunktErstellungVorMinuten(10).build();
		
		BeispielAggregat copie = aggregat.clone();
		
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("aggregatName", Matchers.is(copie.getAggregatName())));
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.is(copie.getZeitpunktErstellung())));
	}
	
	@Test
	public void kopieEnthaeltEigenesDateObjekt() throws IllegalAccessException, InvocationTargetException, BuilderException
	{
		BeispielAggregat aggregat = TestdatenBuilderBeispielAggregat.get().build();
		
		BeispielAggregat copie = aggregat.clone();
		
		MatcherAssert.assertThat(aggregat.getZeitpunktErstellung(), Matchers.not(Matchers.sameInstance(copie.getZeitpunktErstellung())));
		MatcherAssert.assertThat(aggregat.getZeitpunktErstellung(), Matchers.equalTo(copie.getZeitpunktErstellung()));
		MatcherAssert.assertThat(aggregat.getAggregatName(), Matchers.equalTo(copie.getAggregatName()));
	}
	
	
}
