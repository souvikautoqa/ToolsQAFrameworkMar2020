package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	private WebDriver driver = null;
	
	public void initDriver(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");
			driver =  new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//src//test//resources//drivers//geckodriver.exe");
			driver =  new FirefoxDriver();
		}
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(ReadConfig.getProperty("pageLoadTimeout")), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ReadConfig.getProperty("implicitwait")), TimeUnit.SECONDS);
	}
	
	public WebDriver driver() {
		return driver;
	}
	
	public void killDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
