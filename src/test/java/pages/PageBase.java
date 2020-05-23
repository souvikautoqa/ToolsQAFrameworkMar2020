package pages;


import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	
	public boolean isDisplayed (WebElement ele) {
		try {
			return ele.isDisplayed();
		}catch(Exception e) {}
		return false;
	}
	
	public WebElement wait(WebDriver driver, long timeout, By element) {
		return new WebDriverWait(driver,timeout)
		.ignoring(NoSuchElementException.class)
		.pollingEvery(Duration.ofSeconds(1))
		.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	

}
