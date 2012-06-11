package com.interzonedev.spunkfix;

import com.interzonedev.spunkfix.driver.Browser;

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
