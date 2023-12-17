package com.amazon.ui.page;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.amazon.ui.core.BaseTestPage;

public class SearchResultPage extends BaseTestPage {

	private By getResultItems(String product) {
		return By.xpath("//div[contains(@class,'card-container')]//span[contains(text(),'"+product+"')]");
	}
	
	private By getBrandItem(String product) {
		return By.xpath("//div[@id='brandsRefinements']//span//span[contains(@class,'list-item')]//span[contains(text(),'"+product+"')]//preceding-sibling::div//i[contains(@class,'checkbox')]");
	}

	private By getResultsHeader() {
		return By.xpath("//div[contains(@class,'results-header-builder')]//span[contains(text(),'Results')]");
	}
	
	
	private By getProductNames() {
		return By.xpath("//div[contains(@class,'card-container')]//div[@data-cy='title-recipe']//h2");
	}
	
	public ProductDetailsPage clickProductName(String product) {
		LOGGER.info("Clicking product name");
		Set<String> windowHanldesBeforeClick = getDriver().getWindowHandles();
		shortWait();
		int counter = 0;
		List<WebElement> productList = findElements(getResultItems(product));
		for(WebElement element : productList) {
			String productName = getText(element);
			if(productName.contains(product)) {
				while(counter<5) {
					try {
						clickByActions(element);
						switchToChildWindow(windowHanldesBeforeClick);
						break;
					} catch(StaleElementReferenceException e) {
						e.printStackTrace();
					}
					counter++;
				}
				break;
			}
		}
		return new ProductDetailsPage();
	}
	
	public SearchResultPage validateSearchResultPage() {
		LOGGER.info("Validating search result page visibility");
		Assert.assertTrue(isElementPresent(getResultsHeader()), "Search result page is not visible");
        return this;
	}
	
	public SearchResultPage selectBrand(String brand) {
		LOGGER.info("Selecting brand");
		shortWait();
		click(getBrandItem(brand));
		return this;
	}
	
	public SearchResultPage validateBrandSearchResult(String brand) {
		LOGGER.info("Validating search result contains only selected branch products");
		shortWait();
		List<WebElement> productNames = findElements(getProductNames());
		for(WebElement product : productNames) {
			String productName = getText(product);
			if(!(productName.toLowerCase().contains(brand.toLowerCase()))) {
				Assert.assertTrue(false, "Failed to display only selected brand items");
			    break;
			}
		}
		Assert.assertTrue(true);
		return this;
	}
	
}
