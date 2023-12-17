package step_definitions;

import com.amazon.ui.core.BaseTestPage;
import com.amazon.ui.page.SearchResultPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchResultFiltersTest extends BaseTestPage {

	SearchResultPage searchResultPage = new SearchResultPage();
	
	@When("User selects {string} from the brands filter")
	public void user_selects_from_the_brands_filter(String brand) {
	    searchResultPage.selectBrand(brand);
	}

    @Then("Search result displayes {string} brand products")
    public void search_result_displayes_brand_products(String brand) {
		searchResultPage.validateBrandSearchResult(brand);
	}
}
