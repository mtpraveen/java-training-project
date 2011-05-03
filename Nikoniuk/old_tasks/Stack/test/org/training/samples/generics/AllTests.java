package org.training.samples.generics;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@SuiteClasses(value={StackTest.class, TestStackToArray.class, ParametrizedTest.class, ConcurrentTest.class})
public class AllTests {


}
