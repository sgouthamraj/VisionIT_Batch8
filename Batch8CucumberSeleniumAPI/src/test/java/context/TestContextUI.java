package context;

import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import pageobjects.*;

public class TestContextUI {

	private SearchPageObjects searchPageObjects;
	private ProductDescriptionObjects productDescriptionObjects;

	public SearchPageObjects getSearchPageObjects() {
		return searchPageObjects;
	}
	
	public ProductDescriptionObjects getProductDescriptionObjects() {
		return productDescriptionObjects;
	}

	public void initializePageObjectClasses(WebDriver driver,Scenario scn) {
		searchPageObjects = new SearchPageObjects(driver,scn);
		productDescriptionObjects = new ProductDescriptionObjects(driver,scn);
	}
	
}
