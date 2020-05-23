package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobSearchPage extends PageBase{
	
	@FindBy(xpath=".//input[@id='text-input-what']")
	private WebElement txtWhatJob;
	
	@FindBy(xpath=".//input[@id='text-input-where']")
	private WebElement txtWhereJob;
	
	@FindBy(xpath=".//button[text()='Find jobs']")
	private WebElement btnFindJob;
	
	private WebDriver driver = null;
	
	public JobSearchPage(WebDriver driver) { // driver.findElement(xpath)
		this.driver = driver;
		PageFactory.initElements(driver, this); 	
	}
	
	public SearchResultPage performJobSearch(String what, String where) {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].value = '';", txtWhatJob);
//		js.executeScript("arguments[0].value = '';", txtWhereJob);
		txtWhatJob.sendKeys(what);
		txtWhereJob.click();
		txtWhereJob.sendKeys(where);
		for(int i=txtWhereJob.getAttribute("value").length(); i >=0 ; i--) {
			txtWhereJob.sendKeys(Keys.BACK_SPACE);
		}
		txtWhereJob.sendKeys(where);
		btnFindJob.click();
		return (new SearchResultPage(driver));
	}
}
