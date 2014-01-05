package de.hegmanns.it.utils.builder.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.hegmanns.it.utils.builder.tests.TestdatenBuilderBeispielAggregatTest;
import de.hegmanns.it.utils.builder.tests.TestdatenBuilderBeispielBeanBasisTest;
import de.hegmanns.it.utils.builder.tests.TestdatenBuilderBeispielBeanMitListTest;

@RunWith(Suite.class)
@SuiteClasses({TestdatenBuilderBeispielAggregatTest.class, TestdatenBuilderBeispielBeanBasisTest.class, TestdatenBuilderBeispielBeanMitListTest.class})
public class TestdatenBuilderTestSuite {

}
