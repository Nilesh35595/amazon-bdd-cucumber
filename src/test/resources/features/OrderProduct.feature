Feature: Order product
	
	
Scenario: Validate user successfully adds product to the cart
	Given User navigates to amazon dashboard page
	When User searches and selects "iPhone 15 Pro Max"
	Then User reaches search result page
  When User clicks on product name from search results
  And User clicks on Add To Cart
  Then Product gets added to the cart
	
	
	

	
	
	
	 