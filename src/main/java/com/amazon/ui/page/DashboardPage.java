package com.amazon.ui.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amazon.ui.core.BaseTestPage;

public class DashboardPage extends BaseTestPage{

	private By getSearchBox() {
		return By.xpath("//input[contains(@id,'searchtextbox')]");
	}
	
	private By getSuggestions() {
		return By.xpath("//div[contains(@class,'suggestion') and @role='button']");
	}
	
	public DashboardPage searchProduct(String product) {
		LOGGER.info("Searching "+ product);
		setValue(product, getSearchBox());
	    return this;
	}
	
	public SearchResultPage selectProduct(String product) {
		LOGGER.info("Selecting "+ product +" from the suggestions");
		shortWait();
		List<WebElement> suggestionElements = findElements(getSuggestions());
		for(WebElement suggestion : suggestionElements) {
			String value = getAttributeValue("aria-label", suggestion);
			if(value.equalsIgnoreCase(product)) {
				clickByActions(suggestion);
				break;
			}
		}
		return new SearchResultPage();
	}
}
