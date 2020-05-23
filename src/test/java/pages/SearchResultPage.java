package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends PageBase{

	@FindBy(xpath=".//td[@id='resultsCol']//div[contains(@class,'clickcard')]")
	private List<WebElement> searchResults;
	
	private WebDriver driver = null;
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
	
	public boolean verifyResults() {
		boolean status = (searchResults.size() == 10 ? true : false);  // ternary operator
		return status;
	}
}
