package stepdefs.ui;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import context.CmnPageObjectContext;
import context.WebDriverContext;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import context.TestBase;
import context.TestContextUI;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.CmnPageObjects;
import pageobjects.SearchPageObjects;
import utils.manager.driver.factory.DriverFactory;
import utils.manager.driver.factory.DriverManager;
import utils.manager.driver.singleton.WebDriverManagerSingleton;
import utils.manager.driver.staticmethod.WebDriverManagerSimple;

public class SearchStepDefs extends TestBase{

	WebDriverContext webDriverContext;
	CmnPageObjectContext cmnPageObjectContext;
	TestContextUI testContextUI;
	Scenario scn;
	String productClickedTextExpected;
	
	public SearchStepDefs(TestContextUI testContextUI, WebDriverContext webDriverContext, CmnPageObjectContext cmnPageObjectContext) {
		this.testContextUI = testContextUI;
		this.webDriverContext = webDriverContext;
		this.cmnPageObjectContext = cmnPageObjectContext;
	}

	private void initWebDriver() throws Exception {
		DriverManager driverManager = DriverFactory.getDriverManager("chrome");
		WebDriver driver = driverManager.getDriver();
		driverManager.maximizeBrowser();
		driverManager.navigateToDriver(serverUI);
		scn.write("Chrome Driver invoked and URL navigated as: " + serverUI);
		webDriverContext.setDriver(driver);
	}
	
	@Given("^I have browser opened and url is navigated$")
	public void i_have_browser_opened_and_url_is_navigated() throws Exception {
		initWebDriver();
		testContextUI.initializePageObjectClasses(webDriverContext.getDriver(), scn);
		cmnPageObjectContext.initializePageObjectClasses(webDriverContext.getDriver(), scn);
	}

	@Given("^I have browser opened$")
	public void i_have_browser_opened_to_home_page() throws Exception {
		initWebDriver();
		cmnPageObjectContext.initializePageObjectClasses(webDriverContext.getDriver(), scn);
	}

	@When("I search for product as {string}")
	public void i_search_for_product_as(String product) {
		cmnPageObjectContext.getCmnPageObjects().SetSearchTextBox(product);
		cmnPageObjectContext.getCmnPageObjects().ClickOnSearchButton();
		scn.write("Search was sucessfull");
		
	}

	@Then("product list should appear pertaining to the product search as {string}")
	public void product_list_should_appear_pertaining_to_the_product_search_as(String productName) {
		testContextUI.getSearchPageObjects().ValidateProductList(productName);
	}
	
	@When("I click on hamburger menu")
	public void i_click_on_hamburger_menu() {
		cmnPageObjectContext.getCmnPageObjects().ClickOnHamburgerMenuButton();
	}

	@When("I click on hamburger menu with category as {string}")
	public void i_click_on_hamburger_menu_with_category_as(String category) {
		cmnPageObjectContext.getCmnPageObjects().ClickOnHamburgerMenuProductCategoryLink(category);
	}
	
	@When("I click on hamburger menu with sub category as {string}")
	public void i_click_on_hamburger_menu_with_sub_category_as(String subCategory) {
		cmnPageObjectContext.getCmnPageObjects().ClickOnHamburgerMenuProductSubCategoryLink(subCategory);
	}

	@Then("Search results are displayed for products related to {string}")
	public void search_results_are_displayed_for_products_related_to(String expectedTitle) throws Exception {
		cmnPageObjectContext.getCmnPageObjects().validatePageTitleMatch(expectedTitle);
	}
	
	@When("I click on any product in the Search Result")
	public void i_click_on_any_product_in_the_Search_Result() {
		productClickedTextExpected = testContextUI.getSearchPageObjects().ClickOnProductLink();
	}

	@Then("I am able to see product description and detail in new tab")
	public void i_am_able_to_see_product_description_and_detail_in_new_tab() {
		testContextUI.getProductDescriptionObjects().switchToSecondWindowTab();
		testContextUI.getProductDescriptionObjects().ValidateProductDescriptionHeader(productClickedTextExpected);
		testContextUI.getProductDescriptionObjects().switchToDefaultWindowTab();	
	}
	
	
	@Before
	public void SetUp(Scenario s) {
		this.scn = s;
	}

	
	@After
	public void CleanUp(Scenario s) {
		
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot)webDriverContext.getDriver();
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.embed(data, "image/png");
		}

		webDriverContext.getDriver().quit();
		scn.write("Browser is Closed");
	}
	
}
