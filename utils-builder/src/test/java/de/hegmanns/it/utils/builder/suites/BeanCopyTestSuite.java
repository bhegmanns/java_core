package de.hegmanns.it.utils.builder.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.hegmanns.it.utils.builder.tests.BeanCopyAnBeispielAggregatTest;
import de.hegmanns.it.utils.builder.tests.BeanCopyAnBeispielBeanBasis;



@RunWith(Suite.class)
@SuiteClasses({BeanCopyAnBeispielAggregatTest.class, BeanCopyAnBeispielBeanBasis.class})
public class BeanCopyTestSuite {

}
