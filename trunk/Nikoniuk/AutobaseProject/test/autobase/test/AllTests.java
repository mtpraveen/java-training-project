/**
 * 
 */
package autobase.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@SuiteClasses(value={TestAdmin.class, TestDispatcher.class, TestDriver.class, TestPermissions.class})
public class AllTests {

}
