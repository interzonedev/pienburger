package com.interzonedev.pienburger.driver;

import javax.inject.Inject;
import javax.inject.Named;

import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;

/**
 * Factory class for getting instances of concrete implementations of {@link WebDriver} for different browsers.
 * 
 * @author "Mark Markarian" &lt;mark@interzonedev.com&gt;
 */
@Named("webDriverFactory")
public class WebDriverFactory {

	@Inject
	private ApplicationContext applicationContext;

	/**
	 * Gets a concrete implementation of {@link WebDriver} that corresponds with the {@link Browser} with the specified
	 * id.
	 * 
	 * @param browserId
	 *            The id of the {@link Browser} that specifies which browser executable the returned {@link WebDriver}
	 *            controls.
	 * 
	 * @return Returns a concrete implementation of {@link WebDriver} that corresponds with the {@link Browser} with the
	 *         specified id.
	 */
	public WebDriver getWebDriver(String browserId) {
		if (!Browser.allIds().contains(browserId)) {
			throw new IllegalArgumentException("Unrecognized browser id: " + browserId);
		}

		return getWebDriver(Browser.getById(browserId));
	}

	/**
	 * Gets a concrete implementation of {@link WebDriver} that corresponds with the specified {@link Browser}.
	 * 
	 * @param browser
	 *            An instance of {@link Browser} that specifies which browser executable the returned {@link WebDriver}
	 *            controls.
	 * 
	 * @return Returns a concrete implementation of {@link WebDriver} that corresponds with the specified
	 *         {@link Browser}.
	 */
	public WebDriver getWebDriver(Browser browser) {

		String beanName = browser.id() + "Driver";

		WebDriver driver = (WebDriver) applicationContext.getBean(beanName);

		return driver;
	}
}
