package context;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import pageobjects.CmnPageObjects;
import pageobjects.ProductDescriptionObjects;
import pageobjects.SearchPageObjects;

public class WebDriverContext {
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
