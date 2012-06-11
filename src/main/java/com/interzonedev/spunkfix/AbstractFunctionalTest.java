package com.interzonedev.spunkfix;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.sprintfix.AbstractIntegrationTest;
import com.interzonedev.spunkfix.driver.Browser;
import com.interzonedev.spunkfix.driver.BrowserOperations;
import com.interzonedev.spunkfix.driver.WebDriverFactory;

@ContextConfiguration(locations = { "classpath:spring/com/interzonedev/spunkfix/applicationContext-spunkfix.xml" })
public abstract class AbstractFunctionalTest extends AbstractIntegrationTest {

	protected Log log = LogFactory.getLog(getClass());

	@Inject
	private WebDriverFactory webDriverFactory;

	protected WebDriver driver;

	protected BrowserOperations browserOperations;

	protected abstract FunctionalTestProperties getFunctionalTestProperties();

	@Before
	public void beforeTest() {
		FunctionalTestProperties functionalTestProperties = getFunctionalTestProperties();
		browserOperations = new BrowserOperations(functionalTestProperties);
		Browser browser = functionalTestProperties.getBrowser();
		driver = webDriverFactory.getWebDriver(browser);
	}

	@After
	public void afterTest() {
		driver = null;
	}
}
