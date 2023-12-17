package com.amazon.ui.page;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.amazon.ui.core.BaseTestPage;

public class ProductDetailsPage extends BaseTestPage {
	
//	private By getProductPrice(String product) {
//		return By.xpath("//span[contains(text(),'"+product+"')]//ancestor::li//following-sibling::div[contains(@class,'item-price-block')]//span[contains(@class,'product-price')]");
//	}
	
	private By getProductPrice(String product) {
		return By.xpath("//span[contains(text(),'"+product+"')]//ancestor::div[@id='title_feature_div']//following-sibling::div[@id='apex_desktop']//span[contains(@class,'price-whole')]");
	}
	
	private By getAddToCartButton() {
		return By.xpath("//input[@id='add-to-cart-button']");
	}
	
	private By getCartAdditionMessage() {
		return By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[contains(text(),'Added to Cart')]");
	}
	
	private By getViewCartButton() {
		return By.xpath("//span[contains(@id,'view-cart-button')]//input");
	}
	
	public ProductDetailsPage clickAddToCart() {
		LOGGER.info("Clicking Add To Cart button");
		waitUntilElementIsClickable(getAddToCartButton());
		clickByKeys((getAddToCartButton()));
		return this;
	}
	
	public ProductDetailsPage validateCartAdditionMessage() {
		LOGGER.info("Validating product cart addition message");
		Assert.assertTrue(isElementPresent(getCartAdditionMessage()), "Message for the addition of product to the cart is not displayed");
		return this;
	}
	
	public ProductsCartPage clickCart() {
		LOGGER.info("Clicking Cart");
		click(getViewCartButton());
		return new ProductsCartPage();
	}
	
	public String captureProductPrice(String product) {
		LOGGER.info("Capturing product price");
		shortWait();
		String productPrice = getText(getProductPrice(product));
		return productPrice;
	}
}
