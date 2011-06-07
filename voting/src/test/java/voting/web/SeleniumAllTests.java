package voting.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(value=Suite.class)
@SuiteClasses(value={AdminTest.class, GuestTest.class, UserTest.class, TearDown.class})
public class SeleniumAllTests {

}
