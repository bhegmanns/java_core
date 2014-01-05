package de.hegmanns.it.utils.builder.tests;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import de.hegmanns.it.utils.builder.TestdatenBuilderBeispielBeanBasis;
import de.hegmanns.it.utils.builder.BuilderException;
import de.hegmanns.it.utils.testdomain.basisklassen.BeispielBeanBasis;

public class BeanCopyAnBeispielBeanBasis {
	
	@Test
	public void deepCopy() throws BuilderException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
	{
		TestdatenBuilderBeispielBeanBasis beanBuilder = TestdatenBuilderBeispielBeanBasis.get().einBigDecimal(new BigDecimal(25));
		beanBuilder.beispielAggregat();
		BeispielBeanBasis bean = beanBuilder.build();
		
		BeispielBeanBasis clonedBean = bean.clone();
		
		MatcherAssert.assertThat(bean, Matchers.not(Matchers.sameInstance(clonedBean)));
		MatcherAssert.assertThat(bean.getBeispielAggregat(), Matchers.not(Matchers.sameInstance(clonedBean.getBeispielAggregat())));
		
		MatcherAssert.assertThat(bean.getEinBigDecimal(), Matchers.not(Matchers.sameInstance(clonedBean.getEinBigDecimal())));
	}
	
}
