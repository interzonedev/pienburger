package com.interzonedev.spunkfix;

import com.interzonedev.spunkfix.driver.Browser;

/**
 * Value object that holds the properties used in a functional test. Holds values for the default timeout when waiting
 * for a page load or for elements to appear in a page, the URL of the application under test and a {@link Browser}
 * instance to open for the test.
 * 
 * An instance of this class is meant to be supplied to the spunkfix framework for each implementing functional test to
 * specify how to run each test.
 * 
 * @author "Mark Markarian" <mark@interzonedev.com>
 */
public class FunctionalTestProperties {

	private Browser browser;

	private String applicationUrl;

	private Long elementWaitTimeoutInSeconds = 5L;

	public FunctionalTestProperties() {
	}

	public FunctionalTestProperties(Browser browser, String applicationUrl) {
		this.browser = browser;
		this.applicationUrl = applicationUrl;
	}

	public FunctionalTestProperties(Browser browser, String applicationUrl, Long elementWaitTimeoutInSeconds) {
		this.browser = browser;
		this.applicationUrl = applicationUrl;
		this.elementWaitTimeoutInSeconds = elementWaitTimeoutInSeconds;
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public Long getElementWaitTimeoutInSeconds() {
		return elementWaitTimeoutInSeconds;
	}

	public void setElementWaitTimeoutInSeconds(Long elementWaitTimeoutInSeconds) {
		this.elementWaitTimeoutInSeconds = elementWaitTimeoutInSeconds;
	}

}
