package step_definitions;

import com.amazon.ui.core.BaseTestPage;
import com.amazon.ui.page.DashboardPage;
import com.amazon.ui.page.ProductsCartPage;
import com.amazon.ui.page.ProductDetailsPage;
import com.amazon.ui.page.SearchResultPage;

import io.cucumber.java.en.*;

public class OrderProductTest extends BaseTestPage {

	SearchResultPage searchResultPage;
	ProductDetailsPage productDetailsPage;
	DashboardPage dashboardPage = new DashboardPage();
	ProductsCartPage productCartPage;
	String product ;
	String productPrice;
	
	
	@Given("User navigates to amazon dashboard page")
	public void user_navigates_to_amazon_dashboard_page() {

	}

	@When("User searches and selects {string}")
	public void user_searches_and_selects(String product) {
		this.product = product;
		dashboardPage.searchProduct(product);
		searchResultPage = dashboardPage.selectProduct(product);
	}

	@Then("User reaches search result page")
	public void user_reaches_search_result_page() {
	    searchResultPage.validateSearchResultPage();
	}
	
	@When("User clicks on product name from search results")
	public void user_clicks_on_product_name_from_search_results() {	
		shortWait();
		productDetailsPage = searchResultPage.clickProductName(product);
	}

	@When("User clicks on Add To Cart")
	public void user_clicks_on_add_to_cart() {
		productPrice = productDetailsPage.captureProductPrice(product).trim();
		productDetailsPage.clickAddToCart().validateCartAdditionMessage();
		productCartPage = productDetailsPage.clickCart();
	}

	@Then("Product gets added to the cart")
	public void product_gets_added_to_the_cart() {
		
		productCartPage.validateProductAdditionToCart(product, productPrice);
	}
}
