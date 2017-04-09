package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountControllerTest.class, AdminFuncControllerTest.class, DBControllerTest.class,
		LogonControllerTest.class, SearchControllerTest.class })
public class AllTests {

}
