package stepdefs.ui;

import java.util.List;

import context.CmnPageObjectContext;
import context.TestContextUI;
import cucumber.api.Scenario;
import cucumber.api.java.en.Then;

public class HomePageStepDefs {

	CmnPageObjectContext context;

	Scenario scn;

	public HomePageStepDefs(CmnPageObjectContext context) {
		this.context = context;
	}

	@Then("Below header Links are displayed")
	public void below_header_Links_are_displayed(List<String> list) throws Exception {
		for (int i=0;i<list.size();i++) {
			context.getCmnPageObjects().validateElementPresentInHeaderSection(list.get(i));
		}

	}
}
