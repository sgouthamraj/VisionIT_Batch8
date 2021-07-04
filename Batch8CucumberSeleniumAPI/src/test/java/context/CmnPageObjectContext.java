package context;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import pageobjects.CmnPageObjects;
import pageobjects.ProductDescriptionObjects;
import pageobjects.SearchPageObjects;

public class CmnPageObjectContext {
	private CmnPageObjects cmnPageObjects;

	public CmnPageObjects getCmnPageObjects() {
		return cmnPageObjects;
	}

	public void initializePageObjectClasses(WebDriver driver,Scenario scn) {
		cmnPageObjects = new CmnPageObjects(driver,scn);
	}
}
