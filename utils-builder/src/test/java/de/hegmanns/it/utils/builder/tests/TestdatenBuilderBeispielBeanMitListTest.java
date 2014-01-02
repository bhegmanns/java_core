package de.hegmanns.it.utils.builder.tests;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import de.hegmanns.it.utils.builder.TestdatenBuilderBeispielAggregat;
import de.hegmanns.it.utils.builder.TestdatenBuilderBeispielBeanMitList;
import de.hegmanns.it.utils.builder.BuilderException;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielAggregat;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielBeanMitList;

public class TestdatenBuilderBeispielBeanMitListTest {

	@Test
	public void kannAttributeAusUnterklasseInitialisieren() throws BuilderException
	{
		TestdatenBuilderBeispielBeanMitList builder = TestdatenBuilderBeispielBeanMitList.get();
		
		BeispielAggregat aggregat = TestdatenBuilderBeispielAggregat.get()
				.build();
		
		BeispielBeanMitList bean = builder
				.addToBeispielAggregate(aggregat)
				.build();
		
		MatcherAssert.assertThat(bean, Matchers.notNullValue());
		MatcherAssert.assertThat(bean.isEinBoolean(), Matchers.is(true));
		MatcherAssert.assertThat(bean.getEinInt(), Matchers.is(-10));
		MatcherAssert.assertThat(bean.getBeispielAggregat(), Matchers.notNullValue());
		MatcherAssert.assertThat(bean.getBeispielAggregate(), Matchers.notNullValue());
		MatcherAssert.assertThat(bean.getBeispielAggregate(), Matchers.hasSize(1));
	}
	
	@Test
	public void initialisierenMitBeispielAggregateCollection() throws BuilderException
	{
		TestdatenBuilderBeispielBeanMitList builder = TestdatenBuilderBeispielBeanMitList.get();
		
		TestdatenBuilderBeispielAggregat aggregatBuilder = null;
		aggregatBuilder = TestdatenBuilderBeispielAggregat.get();
		
		builder.addToBeispielAggregate(aggregatBuilder.build());
		builder.addToBeispielAggregate(aggregatBuilder.build());
		
		BeispielBeanMitList bean = builder.build();
		
		MatcherAssert.assertThat(bean, Matchers.notNullValue());
		MatcherAssert.assertThat(bean.isEinBoolean(), Matchers.is(true));
		MatcherAssert.assertThat(bean.getEinInt(), Matchers.is(-10));
		MatcherAssert.assertThat(bean.getBeispielAggregat(), Matchers.notNullValue());
		MatcherAssert.assertThat(bean.getBeispielAggregate(), Matchers.notNullValue());
		MatcherAssert.assertThat(bean.getBeispielAggregate(), Matchers.hasSize(2));
		
		List<BeispielAggregat> aggregate = bean.getBeispielAggregate();
		BeispielAggregat aggregat0 = aggregate.get(0);
		BeispielAggregat aggregat1 = aggregate.get(1);
		MatcherAssert.assertThat(aggregat0, Matchers.not(Matchers.sameInstance(aggregat1)));
	}
}
