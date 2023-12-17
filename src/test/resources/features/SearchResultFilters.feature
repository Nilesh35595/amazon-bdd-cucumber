Feature: Search result page filters
	
	
Scenario: Validate Brands filter functioning on search result page
	Given User navigates to amazon dashboard page
	When User searches and selects "phone"
	Then User reaches search result page
	When User selects "Samsung" from the brands filter
	Then Search result displayes "Samsung" brand products
	
	

	
	
	
	 