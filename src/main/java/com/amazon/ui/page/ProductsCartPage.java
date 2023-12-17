package com.amazon.ui.page;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.amazon.ui.core.BaseTestPage;

public class ProductsCartPage extends BaseTestPage {
	
	private By getCartProduct(String product, String price) {
		return By.xpath("//span[contains(text(),'"+product+"')]//ancestor::li//following-sibling::div//span[contains(text(),'"+price+"')]");
	}
	
	public ProductsCartPage validateProductAdditionToCart(String product, String price) {
		LOGGER.info("Validate product added successfully to the cart");
		waitUntilElementIsVisible(getCartProduct(product, price));
		Assert.assertTrue(isElementPresent(getCartProduct(product, price)), "Failed to add product to the cart");
		return this;
	}
}
