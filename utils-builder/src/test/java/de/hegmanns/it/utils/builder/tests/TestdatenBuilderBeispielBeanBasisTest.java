package de.hegmanns.it.utils.builder.tests;

import java.math.BigDecimal;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import de.hegmanns.it.utils.builder.TestdatenBuilderBeispielBeanBasis;
import de.hegmanns.it.utils.builder.BuilderException;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielAggregat;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielBeanBasis;

public class TestdatenBuilderBeispielBeanBasisTest {

	@Test
	public void nurDefault() throws BuilderException
	{
		BeispielBeanBasis bean = TestdatenBuilderBeispielBeanBasis.get().build();
		
		MatcherAssert.assertThat("", bean, Matchers.notNullValue());
		MatcherAssert.assertThat(">>> " + bean.getEinBigDecimal(), bean, Matchers.hasProperty("einBigDecimal", Matchers.is(BigDecimal.TEN)));
		MatcherAssert.assertThat(">>> " + bean.isEinBoolean(),  bean, Matchers.hasProperty("einBoolean", Matchers.is(true)));
		MatcherAssert.assertThat(">>> " + bean.getEinInt(), bean, Matchers.hasProperty("einInt", Matchers.is(new Integer(-10))));
		MatcherAssert.assertThat(">>> (null)" + bean.getBeispielAggregat(), bean, Matchers.hasProperty("beispielAggregat", Matchers.notNullValue()));		
	}
	
	@Test
	public void wertDoppeltDefinitiertErzeugtWarnlog() throws BuilderException
	{
		BeispielBeanBasis bean = TestdatenBuilderBeispielBeanBasis.get()
			.einBigDecimal(BigDecimal.ONE)
			.einBigDecimal(BigDecimal.TEN)
			.build();
	}
	
	@Test
	public void alleWerteVordefiniert() throws BuilderException
	{
		BeispielAggregat aggregat = new BeispielAggregat();
		BeispielBeanBasis bean = TestdatenBuilderBeispielBeanBasis.get()
				.einBigDecimal(BigDecimal.valueOf(20))
				.einInt(new Integer(2))
				.einBoolean(false)
				.beispielAggregat(aggregat)
				.build();
		
		MatcherAssert.assertThat("", bean, Matchers.notNullValue());
		MatcherAssert.assertThat("" + bean.getEinBigDecimal(), bean, Matchers.hasProperty("einBigDecimal", Matchers.is(new BigDecimal(20))));
		MatcherAssert.assertThat("" + bean.getEinInt(), bean, Matchers.hasProperty("einInt", Matchers.is(new Integer(2))));
		MatcherAssert.assertThat("" + bean.isEinBoolean(), bean, Matchers.hasProperty("einBoolean", Matchers.is(false)));
		MatcherAssert.assertThat("" + bean.getBeispielAggregat(), bean, Matchers.hasProperty("beispielAggregat", Matchers.sameInstance(aggregat)));
	
	}
	
	@Test
	public void alleWerteVordefiniertOhneAggregat() throws BuilderException
	{
		BeispielBeanBasis bean = TestdatenBuilderBeispielBeanBasis.get()
				.einBigDecimal(BigDecimal.valueOf(20))
				.einInt(new Integer(2))
				.einBoolean(false)
				.build();
		
		MatcherAssert.assertThat("", bean, Matchers.notNullValue());
		MatcherAssert.assertThat(">>> " + bean.getEinBigDecimal(), bean, Matchers.hasProperty("einBigDecimal", Matchers.is(new BigDecimal(20))));
		MatcherAssert.assertThat(">>> " + bean.getEinInt(), bean, Matchers.hasProperty("einInt", Matchers.is(new Integer(2))));
		MatcherAssert.assertThat(">>> " + bean.isEinBoolean(), bean, Matchers.hasProperty("einBoolean", Matchers.is(false)));
		MatcherAssert.assertThat(">>> " + bean.getBeispielAggregat(), bean, Matchers.hasProperty("beispielAggregat", Matchers.notNullValue()));
	}
	
	@Test
	public void alleWerteVordefiniertMitAggregatBuilderDefaultwerte() throws BuilderException
	{
		TestdatenBuilderBeispielBeanBasis beanBuilder = TestdatenBuilderBeispielBeanBasis.get();
		beanBuilder.beispielAggregat();
		
		BeispielBeanBasis bean = beanBuilder.build();
		
		MatcherAssert.assertThat("", bean, Matchers.notNullValue());
		MatcherAssert.assertThat(">>> " + bean.getEinBigDecimal(), bean, Matchers.hasProperty("einBigDecimal", Matchers.is(BigDecimal.TEN)));
		MatcherAssert.assertThat(">>> " + bean.isEinBoolean(),  bean, Matchers.hasProperty("einBoolean", Matchers.is(true)));
		MatcherAssert.assertThat(">>> " + bean.getEinInt(), bean, Matchers.hasProperty("einInt", Matchers.is(new Integer(-10))));
		MatcherAssert.assertThat(">>> " + bean.getBeispielAggregat(), bean, Matchers.hasProperty("beispielAggregat", Matchers.notNullValue()));	
		
		BeispielAggregat aggregat = bean.getBeispielAggregat();
		MatcherAssert.assertThat(">>> " + aggregat.getAggregatName(), aggregat, Matchers.hasProperty("aggregatName", Matchers.is("NN")));
		MatcherAssert.assertThat(">>> " + aggregat.getZeitpunktErstellung(), aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.notNullValue()));
	}
	
	@Test
	public void alleWerteVordefiniertMitAggregatBuilderNameDefiniert() throws BuilderException
	{
		TestdatenBuilderBeispielBeanBasis beanBuilder = TestdatenBuilderBeispielBeanBasis.get();
		beanBuilder.beispielAggregat().name("Heinrich");
		
		BeispielBeanBasis bean = beanBuilder.build();
		
		MatcherAssert.assertThat("", bean, Matchers.notNullValue());
		MatcherAssert.assertThat(">>> " + bean.getEinBigDecimal(), bean, Matchers.hasProperty("einBigDecimal", Matchers.is(BigDecimal.TEN)));
		MatcherAssert.assertThat(">>> " + bean.isEinBoolean(),  bean, Matchers.hasProperty("einBoolean", Matchers.is(true)));
		MatcherAssert.assertThat(">>> " + bean.getEinInt(), bean, Matchers.hasProperty("einInt", Matchers.is(new Integer(-10))));
		MatcherAssert.assertThat(">>> " + bean.getBeispielAggregat(), bean, Matchers.hasProperty("beispielAggregat", Matchers.notNullValue()));	
		
		BeispielAggregat aggregat = bean.getBeispielAggregat();
		MatcherAssert.assertThat(">>> " + aggregat.getAggregatName(), aggregat, Matchers.hasProperty("aggregatName", Matchers.is("Heinrich")));
		MatcherAssert.assertThat(">>> ", aggregat, Matchers.hasProperty("zeitpunktErstellung", Matchers.notNullValue()));
	}
	
}
