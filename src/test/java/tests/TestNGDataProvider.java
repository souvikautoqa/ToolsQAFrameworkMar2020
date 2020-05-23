package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.DriverFactory;

public class TestNGDataProvider {
	
	WebDriver driver = null;
	
	@BeforeClass
	public void init() {
		DriverFactory df = new DriverFactory();
		df.initDriver("chrome");
		driver = df.driver();
	}
	
	@Test(dataProvider="amazonData")
	public void AmazonTest(String cat, String val) {
		driver.get("https://amazon.co.uk");
		
		Select sel = new Select(driver.findElement(By.xpath(".//select[@id='searchDropdownBox']")));
		sel.selectByVisibleText(cat);
		driver.findElement(By.xpath(".//input[@id='twotabsearchtextbox']")).sendKeys(val);
		driver.findElement(By.xpath(".//input[@id='twotabsearchtextbox']")).sendKeys(Keys.ESCAPE);
		
	}
	
	@DataProvider
	public Object[][] amazonData(){
		Object[][] data = new Object[3][2];
		
		data[0][0] = "Books";
		data[0][1] = "Harry Potter";
		data[1][0] = "Beauty";
		data[1][1] = "Bleach";
		data[2][0] = "Baby";
		data[2][1] = "Mammy Poko";
		
		return data;
	}
	

}
