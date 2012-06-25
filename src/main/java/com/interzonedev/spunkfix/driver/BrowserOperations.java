package com.interzonedev.spunkfix.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.interzonedev.spunkfix.FunctionalTestProperties;

/**
 * Implements helper methods that perform common operations on the live web browser opened for each test. Each instance
 * gets the a specific instance of {@link FunctionalTestProperties} that holds the URL for the application under test
 * and the default timeout (in seconds) for waiting for page and element loads.
 * 
 * @author "Mark Markarian" <mark@interzonedev.com>
 */
public class BrowserOperations {

	private String applicationUrl;

	private Long elementWaitTimeoutInSeconds;

	/**
	 * Constructs an instance with the specified {@link FunctionalTestProperties} so the a common application URL and
	 * default wait timeout (in seconds) can be used throughout the helper methods.
	 * 
	 * @param functionalTestProperties
	 *            The {@link FunctionalTestProperties} instance that holds the properties to be used by the helper
	 *            methods.
	 */
	public BrowserOperations(FunctionalTestProperties functionalTestProperties) {
		applicationUrl = functionalTestProperties.getApplicationUrl();
		elementWaitTimeoutInSeconds = functionalTestProperties.getElementWaitTimeoutInSeconds();
	}

	/**
	 * Opens the page in the browser controlled by the specified {@link WebDriver} to the specified URL relative to the
	 * application URL set in the {@link FunctionalTestProperties} instance passed into the constructor of this
	 * instance.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which the page is to be opened.
	 * @param url
	 *            The URL relative to the application URL to which the page is to be opened.
	 */
	public void openPage(WebDriver driver, String url) {
		driver.get(applicationUrl + url);
	}

	/**
	 * Waits for the element identified by the selector in the specified {@link By} instance to be present in the
	 * current page opened in the browser controlled by the specified {@link WebDriver}. Timesout after the default
	 * timeout set on this instance.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for the element.
	 * @param by
	 *            The {@link By} instance that determines how the element to wait for is selected.
	 * 
	 * @throws TimeoutException
	 *             Thrown if the element is not found in the amount of time specified by the default timeout set on this
	 *             instance.
	 */
	public void waitForElement(WebDriver driver, By by) {
		waitForElement(driver, by, elementWaitTimeoutInSeconds);
	}

	/**
	 * Waits for the element identified by the selector in the specified {@link By} instance to be present in the
	 * current page opened in the browser controlled by the specified {@link WebDriver}. Timesout after the specified
	 * number of seconds.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for the element.
	 * @param by
	 *            The {@link By} instance that determines how the element to wait for is selected.
	 * @param timeOutInSeconds
	 *            The number of seconds to wait for the element before timing out.
	 * 
	 * @throws TimeoutException
	 *             Thrown if the element is not found in the amount of time specified by the timeout.
	 */
	public void waitForElement(WebDriver driver, By by, long timeOutInSeconds) {
		waitForElementWithText(driver, by, null, timeOutInSeconds);
	}

	/**
	 * Waits for the element identified by the selector in the specified {@link By} instance with the specified text to
	 * be present in the current page opened in the browser controlled by the specified {@link WebDriver}. Timesout
	 * after the default timeout set on this instance.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for the element.
	 * @param by
	 *            The {@link By} instance that determines how the element to wait for is selected.
	 * @param text
	 *            The text that the element to wait for must contain.
	 * 
	 * @throws TimeoutException
	 *             Thrown if the element is not found in the amount of time specified by the default timeout set on this
	 *             instance.
	 */
	public void waitForElementWithText(WebDriver driver, By by, String text) {
		waitForElementWithText(driver, by, text, elementWaitTimeoutInSeconds);
	}

	/**
	 * Waits for the element identified by the selector in the specified {@link By} instance with the specified text to
	 * be present in the current page opened in the browser controlled by the specified {@link WebDriver}. Timesout
	 * after the specified number of seconds.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for the element.
	 * @param by
	 *            The {@link By} instance that determines how the element to wait for is selected.
	 * @param text
	 *            The text that the element to wait for must contain.
	 * @param timeOutInSeconds
	 *            The number of seconds to wait for the element before timing out.
	 * 
	 * @throws TimeoutException
	 *             Thrown if the element is not found in the amount of time specified by the timeout.
	 */
	public void waitForElementWithText(WebDriver driver, final By by, final String text, long timeOutInSeconds) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement element = d.findElement(by);
				return ((null == text) || element.getText().equals(text));
			}
		};

		Wait<WebDriver> pageLoadWait = new WebDriverWait(driver, timeOutInSeconds);
		pageLoadWait.until(pageLoadCondition);
	}

	/**
	 * Waits for and returns the element identified by the selector in the specified {@link By} instance to be present
	 * in the current page opened in the browser controlled by the specified {@link WebDriver}. Timesout after the
	 * default timeout set on this instance.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for and get the element.
	 * @param by
	 *            The {@link By} instance that determines how the element to wait for and get is selected.
	 * 
	 * @return Returns the element identified by the selector in the specified {@link By}.
	 * 
	 * @throws TimeoutException
	 *             Thrown if the element is not found in the amount of time specified by the default timeout set on this
	 *             instance.
	 */
	public WebElement waitForAndGetElement(WebDriver driver, By by) {
		return waitForAndGetElementWithText(driver, by, null, elementWaitTimeoutInSeconds);
	}

	/**
	 * Waits for and returns the element identified by the selector in the specified {@link By} instance to be present
	 * in the current page opened in the browser controlled by the specified {@link WebDriver}. Timesout after the
	 * specified number of seconds.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for and get the element.
	 * @param by
	 *            The {@link By} instance that determines how the element to wait for and get is selected.
	 * @param timeOutInSeconds
	 *            The number of seconds to wait for the element before timing out.
	 * 
	 * @return Returns the element identified by the selector in the specified {@link By}.
	 * 
	 * @throws TimeoutException
	 *             Thrown if the element is not found in the amount of time specified by the timeout.
	 */
	public WebElement waitForAndGetElement(WebDriver driver, By by, long timeOutInSeconds) {
		return waitForAndGetElementWithText(driver, by, null, timeOutInSeconds);
	}

	/**
	 * Waits for and returns the element identified by the selector in the specified {@link By} instance with the
	 * specified text to be present in the current page opened in the browser controlled by the specified
	 * {@link WebDriver}. Timesout after the default timeout set on this instance.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for and get the element.
	 * @param by
	 *            The {@link By} instance that determines how the element to wait for and get is selected.
	 * @param text
	 *            The text that the element to wait and get for must contain.
	 * 
	 * @return Returns the element identified by the selector in the specified {@link By}.
	 * 
	 * @throws TimeoutException
	 *             Thrown if the element is not found in the amount of time specified by the default timeout set on this
	 *             instance.
	 */
	public WebElement waitForAndGetElementWithText(WebDriver driver, By by, String text) {
		return waitForAndGetElementWithText(driver, by, text, elementWaitTimeoutInSeconds);
	}

	/**
	 * Waits for and returns the element identified by the selector in the specified {@link By} instance with the
	 * specified text to be present in the current page opened in the browser controlled by the specified
	 * {@link WebDriver}. Timesout after the specified number of seconds.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for and get the element.
	 * @param by
	 *            The {@link By} instance that determines how the element to wait for and get is selected.
	 * @param text
	 *            The text that the element to wait and get for must contain.
	 * @param timeOutInSeconds
	 *            The number of seconds to wait for the element before timing out.
	 * 
	 * @return Returns the element identified by the selector in the specified {@link By}.
	 * 
	 * @throws TimeoutException
	 *             Thrown if the element is not found in the amount of time specified by the timeout.
	 */
	public WebElement waitForAndGetElementWithText(WebDriver driver, By by, String text, long timeOutInSeconds) {
		waitForElementWithText(driver, by, text, timeOutInSeconds);
		return driver.findElement(by);
	}

	/**
	 * Waits for at least one element identified by the selector in the specified {@link By} instance to be present in
	 * the current page opened in the browser controlled by the specified {@link WebDriver}. Timesout after the default
	 * timeout set on this instance.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for the elements.
	 * @param by
	 *            The {@link By} instance that determines how the elements to wait for are selected.
	 * 
	 * @throws TimeoutException
	 *             Thrown if at least one element is not found in the amount of time specified by the default timeout
	 *             set on this instance.
	 */
	public void waitForElements(WebDriver driver, final By by) {
		waitForElements(driver, by, elementWaitTimeoutInSeconds);
	}

	/**
	 * Waits for at least one element identified by the selector in the specified {@link By} instance to be present in
	 * the current page opened in the browser controlled by the specified {@link WebDriver}. Timesout after the
	 * specified number of seconds.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for the elements.
	 * @param by
	 *            The {@link By} instance that determines how the elements to wait for are selected.
	 * @param timeOutInSeconds
	 *            The number of seconds to wait for the elements before timing out.
	 * 
	 * @throws TimeoutException
	 *             Thrown if at least one element is not found in the amount of time specified by the timeout.
	 */
	public void waitForElements(WebDriver driver, final By by, long timeOutInSeconds) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				List<WebElement> elements = d.findElements(by);
				return !elements.isEmpty();
			}
		};

		Wait<WebDriver> pageLoadWait = new WebDriverWait(driver, timeOutInSeconds);
		pageLoadWait.until(pageLoadCondition);
	}

	/**
	 * Waits for at least one element identified by the selector in the specified {@link By} instance to be present in
	 * the current page opened in the browser controlled by the specified {@link WebDriver} and returns the elements
	 * that are found. Timesout after the default timeout set on this instance.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for and get the elements.
	 * @param by
	 *            The {@link By} instance that determines how the elements to wait for and get are selected.
	 * 
	 * @return Returns a list of elements identified by the selector in the specified {@link By}.
	 * 
	 * @throws TimeoutException
	 *             Thrown if at least one element is not found in the amount of time specified by the default timeout
	 *             set on this instance.
	 */
	public List<WebElement> waitForAndGetElements(WebDriver driver, By by) {
		return waitForAndGetElements(driver, by, elementWaitTimeoutInSeconds);
	}

	/**
	 * Waits for at least one element identified by the selector in the specified {@link By} instance to be present in
	 * the current page opened in the browser controlled by the specified {@link WebDriver} and returns the elements
	 * that are found. Timesout after the specified number of seconds.
	 * 
	 * @param driver
	 *            The {@link WebDriver} instance that controls the browser in which to wait for and get the elements.
	 * @param by
	 *            The {@link By} instance that determines how the elements to wait for and get are selected.
	 * @param timeOutInSeconds
	 *            The number of seconds to wait for the elements before timing out.
	 * 
	 * @return Returns a list of elements identified by the selector in the specified {@link By}.
	 * 
	 * @throws TimeoutException
	 *             Thrown if at least one element is not found in the amount of time specified by the default timeout
	 *             set on this instance.
	 */
	public List<WebElement> waitForAndGetElements(WebDriver driver, By by, long timeOutInSeconds) {
		waitForElements(driver, by, timeOutInSeconds);
		return driver.findElements(by);
	}
}
