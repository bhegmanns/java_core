package de.hegmanns.it.utils.builder.tests;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import de.hegmanns.it.utils.builder.TestdatenBuilderBeispielAggregat;
import de.hegmanns.it.utils.builder.BuilderException;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielAggregat;

public class TestdatenBuilderBeispielAggregatTest {

	@Test
	public void nurDefaultWerte() throws BuilderException
	{
		BeispielAggregat aggregat = TestdatenBuilderBeispielAggregat.get().build();
		
		MatcherAssert.assertThat(aggregat, Matchers.notNullValue());
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("aggregatName", Matchers.notNullValue()));
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("aggregatName", Matchers.is("NN")));
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.notNullValue()));
	}
	
	@Test
	public void alleWerteVordefiniertMitZeitpunktErstellungVorMinuten() throws BuilderException
	{
		BeispielAggregat aggregat =
				TestdatenBuilderBeispielAggregat.get()
				.name("Willi")
				.zeitpunktErstellungVorMinuten(10)
				.build();
		
		
		MatcherAssert.assertThat(aggregat, Matchers.notNullValue());
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("aggregatName", Matchers.notNullValue()));
		MatcherAssert.assertThat(">>> " + aggregat.getAggregatName(), aggregat, Matchers.hasProperty("aggregatName", Matchers.is("Willi")));
		MatcherAssert.assertThat(">>> " + aggregat.getZeitpunktErstellung(), aggregat, Matchers.hasProperty("zeitpunktErstellung"));
		MatcherAssert.assertThat(">>> " + aggregat.getZeitpunktErstellung(), aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.notNullValue()));
	}
	
	@Test
	public void alleWerteVordefiniertMitZeitpunktErstellungAlsDatum() throws BuilderException
	{
		Date zeitpunktErstellung = DateUtils.addMinutes(new Date(), -20);
		BeispielAggregat aggregat = TestdatenBuilderBeispielAggregat.get()
				.name("Willi")
				.zeitpunktErstellung(zeitpunktErstellung)
				.build();
		
		MatcherAssert.assertThat(aggregat, Matchers.notNullValue());
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("aggregatName", Matchers.notNullValue()));
		MatcherAssert.assertThat(">>> " + aggregat.getAggregatName(), aggregat, Matchers.hasProperty("aggregatName", Matchers.is("Willi")));
		MatcherAssert.assertThat(">>> " + aggregat.getZeitpunktErstellung(), aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.is(zeitpunktErstellung)));
		MatcherAssert.assertThat(">>> " + aggregat.getZeitpunktErstellung(), aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.sameInstance(zeitpunktErstellung)));
	}
	
	@Test
	public void nameDefiniertZeitpunktErstellungDefault() throws BuilderException
	{
		BeispielAggregat aggregat = TestdatenBuilderBeispielAggregat.get()
				.name("Willi")
				.build();
		
		MatcherAssert.assertThat(aggregat, Matchers.notNullValue());
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("aggregatName", Matchers.notNullValue()));
		MatcherAssert.assertThat("aggregatName ist " + aggregat.getAggregatName(), aggregat, Matchers.hasProperty("aggregatName", Matchers.is("Willi")));
	}
	
	@Test
	public void zeitpunktErstellungVorMinutenNameDefault() throws BuilderException
	{
		BeispielAggregat aggregat =
				TestdatenBuilderBeispielAggregat.get()
				.zeitpunktErstellungVorMinuten(10)
				.build();
		
		
		MatcherAssert.assertThat(aggregat, Matchers.notNullValue());
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("aggregatName", Matchers.notNullValue()));
		MatcherAssert.assertThat("aggregatName ist " + aggregat.getAggregatName(), aggregat, Matchers.hasProperty("aggregatName", Matchers.is("NN")));
		MatcherAssert.assertThat("", aggregat, Matchers.hasProperty("zeitpunktErstellung"));
		MatcherAssert.assertThat("" + aggregat.getZeitpunktErstellung(), aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.notNullValue()));
	}
	
	@Test
	public void zeitpunktErstellungAlsDatumNameDefault() throws BuilderException
	{
		Date zeitpunktErstellung = DateUtils.addMinutes(new Date(), -20);
		BeispielAggregat aggregat = TestdatenBuilderBeispielAggregat.get()
				.zeitpunktErstellung(zeitpunktErstellung)
				.build();
		
		MatcherAssert.assertThat(aggregat, Matchers.notNullValue());
		MatcherAssert.assertThat(aggregat, Matchers.hasProperty("aggregatName", Matchers.notNullValue()));
		MatcherAssert.assertThat("aggregatName ist " + aggregat.getAggregatName(), aggregat, Matchers.hasProperty("aggregatName", Matchers.is("NN")));
		MatcherAssert.assertThat("" + aggregat.getZeitpunktErstellung(), aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.is(zeitpunktErstellung)));
		MatcherAssert.assertThat("", aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.sameInstance(zeitpunktErstellung)));
	}
	
	@Test
	public void nullWerteVorgegeben() throws BuilderException
	{
		BeispielAggregat aggregat = TestdatenBuilderBeispielAggregat.get()
				.zeitpunktErstellung(null)
				.name(null)
				.build();
		
		
		MatcherAssert.assertThat("", aggregat, Matchers.notNullValue());
		MatcherAssert.assertThat(">>> " + aggregat.getAggregatName(), aggregat, Matchers.hasProperty("aggregatName", Matchers.is("NN")));
		MatcherAssert.assertThat(">>> " + aggregat.getZeitpunktErstellung(), aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.notNullValue()));
		
	}
}
