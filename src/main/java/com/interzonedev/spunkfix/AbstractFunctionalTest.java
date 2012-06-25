package com.interzonedev.spunkfix;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.sprintfix.AbstractIntegrationTest;
import com.interzonedev.sprintfix.dataset.DataSet;
import com.interzonedev.sprintfix.dataset.DataSets;
import com.interzonedev.spunkfix.driver.Browser;
import com.interzonedev.spunkfix.driver.BrowserOperations;
import com.interzonedev.spunkfix.driver.WebDriverFactory;

/**
 * Top level functional test class meant to be run with a JUnit test runner. This is meant to be subclassed by any
 * functional tests that require a live browser running on the desktop to load the page under test.
 * 
 * This leverages the sprintfix integration testing framework so that {@link DataSets} or {@link DataSet} annotations
 * can be used on test classes and methods to set up and tear down any datasources involved in the functional tests. The
 * sprintfix dataset testers can also be used.
 * 
 * Running this will create the spunkfix Spring application context.
 * 
 * @author "Mark Markarian" <mark@interzonedev.com>
 */
@ContextConfiguration(locations = { "classpath:spring/com/interzonedev/spunkfix/applicationContext-spunkfix.xml" })
public abstract class AbstractFunctionalTest extends AbstractIntegrationTest {

	protected Log log = LogFactory.getLog(getClass());

	@Inject
	private WebDriverFactory webDriverFactory;

	/**
	 * A specific {@code WebDriver} implementation that can be used by implementing functional tests to interact with
	 * the live browser opened for each test. This will be set for each test class according to the
	 * {@link FunctionalTestProperties} returned by the {@link AbstractFunctionalTest#getFunctionalTestProperties()}
	 * method.
	 */
	protected WebDriver driver;

	/**
	 * A specific {@code BrowserOperations} implementation that can be used by implementing functional tests to to
	 * perform common operations on the live web browser opened for each test. This will be set for each test class
	 * according to the {@link FunctionalTestProperties} returned by the
	 * {@link AbstractFunctionalTest#getFunctionalTestProperties()} method.
	 */
	protected BrowserOperations browserOperations;

	/**
	 * Abstract method that allows the implementing functional test to return a {@link FunctionalTestProperties}
	 * instance containing the properties (browser, default timeout, URL for the application under test) for that test
	 * class.
	 * 
	 * @return Should return a {@link FunctionalTestProperties} instance containing the properties (browser, default
	 *         timeout, URL for the application under test) for that test class.
	 */
	protected abstract FunctionalTestProperties getFunctionalTestProperties();

	/**
	 * Run before each test method in an the implementing functional test. Sets the {@link #driver} and
	 * {@link #browserOperations} properties according to the {@link FunctionalTestProperties} returned by the
	 * {@link AbstractFunctionalTest#getFunctionalTestProperties()} method.
	 */
	@Before
	public void beforeTest() {
		FunctionalTestProperties functionalTestProperties = getFunctionalTestProperties();
		browserOperations = new BrowserOperations(functionalTestProperties);
		Browser browser = functionalTestProperties.getBrowser();
		driver = webDriverFactory.getWebDriver(browser);
	}

	/**
	 * Run after each test method in an the implementing functional test. Clears the {@link #driver} and
	 * {@link #browserOperations} properties.
	 */
	@After
	public void afterTest() {
		driver = null;
		browserOperations = null;
	}
}
