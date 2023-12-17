package com.amazon.ui.core;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.amazon.ui.utility.Config;

public class BaseTestPage {

	
    protected static final Logger LOGGER = LogManager.getLogger(BaseTestPage.class);
	private static final int WAIT_DURATION = 10;
	
	
	protected WebDriver getDriver() {
		return DriverManager.getDriver();
	}
	
	protected WebElement findElement(By locator) {
		return getDriver().findElement(locator);
	}
	
	protected List<WebElement> findElements(By locator){
		return getDriver().findElements(locator);
	}
	
	protected void click(By locator) {
		waitUntilElementIsClickable(locator);
	    getDriver().findElement(locator).click();
	}
	
	protected void click(WebElement element) {
		element.click();
	}
	
	public void waitUntilElementIsVisible(By locator) {
		Wait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_DURATION));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitUntilElementIsClickable(By locator) {
		Wait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_DURATION));
		wait.until(ExpectedConditions.elementToBeClickable(findElement(locator)));
	}
	
	public boolean isElementPresent(By locator) {
		waitUntilElementIsVisible(locator);
		return findElement(locator).isDisplayed();
	}
	
	protected void setValue(String text, By locator) {
		waitUntilElementIsVisible(locator);
		getDriver().findElement(locator).sendKeys(text);
	}
	
	protected void moveToElement(By locator) {
		WebElement element = findElement(locator);
		
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element).perform();
	}
	
	protected String getAttributeValue(String attribute, WebElement element) {
		return element.getAttribute(attribute);
	}
	
	protected String getText(By locator) {
		String text = findElement(locator).getText();
		return text;
	}
	
	protected String getText(WebElement element) {
		return element.getText();
	}
	
	protected void switchWindow(String windowHandle) {
		getDriver().switchTo().window(windowHandle);
	}
	
	protected void shortWait() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickByJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)getDriver();
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickByActions(WebElement element) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element).click().perform();
	}
	
	public void switchToChildWindow(Set<String> windowHandlesBeforeClick) {
		ArrayList<String> windowHandlesListBrforeClick = new ArrayList<String>(windowHandlesBeforeClick);
		Set<String> windowHandlesAfterClick = getDriver().getWindowHandles();
		ArrayList<String> windowHandlesListAfterClick = new ArrayList<String>(windowHandlesAfterClick);
		windowHandlesListAfterClick.removeAll(windowHandlesListBrforeClick);
		getDriver().switchTo().window(windowHandlesListAfterClick.get(0));
		
	}
	
	public void clickByKeys(By locator) {
		WebElement element = findElement(locator);
		element.sendKeys(Keys.ENTER);
	}
	
	public void refreshPage() {
		getDriver().navigate().refresh();
	}
	
	public static void captureScreenshot(String filePath) {

		try {
			TakesScreenshot takeScreenshot = (TakesScreenshot) DriverManager.getDriver();
			File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(filePath));

		} catch (Exception e) {
			LOGGER.warn("Exception while taking screenshot " + e.getMessage());
		}
	}
}
