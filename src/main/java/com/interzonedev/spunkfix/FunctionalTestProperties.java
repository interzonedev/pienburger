package com.interzonedev.spunkfix;

public class FunctionalTestProperties {

	private String applicationUrl;

	private Long elementWaitTimeoutInSeconds = 5L;

	public FunctionalTestProperties() {
	}

	public FunctionalTestProperties(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public FunctionalTestProperties(String applicationUrl, Long elementWaitTimeoutInSeconds) {
		this.applicationUrl = applicationUrl;
		this.elementWaitTimeoutInSeconds = elementWaitTimeoutInSeconds;
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
