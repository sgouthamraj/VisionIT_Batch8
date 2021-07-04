@ui
Feature: Home Page Validation

	@TC0003
	Scenario: Validate Header links
	  Given I have browser opened
		Then Below header Links are displayed
			|hamburger menu   |
			|amazon prime logo|
			|accounts and list link|
			|return and orders|
			|your prime link|
			|cart link|

