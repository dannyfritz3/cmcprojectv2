package baseclasses;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountTest.class, EntityTests.class, UniversityTest.class, UserTest.class })
public class AllTests {

}
