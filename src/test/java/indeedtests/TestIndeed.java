package indeedtests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import core.DriverFactory;
import pages.JobSearchPage;
import pages.SearchResultPage;

public class TestIndeed extends TestBase{
	
	JobSearchPage jobSearch = null;
	SearchResultPage searchResult = null;
	DriverFactory factory = null;

	@Test(dataProvider="getData")
	public void VerifyIndeedJobSearch(HashMap<String,String> data) throws Exception {
		jobSearch = new JobSearchPage(driver());
		report().report(LogStatus.INFO, "Performing Job Search", "Job Details"+data.get("jobdetails")+" -- Location "+data.get("location"));
		searchResult = jobSearch.performJobSearch(data.get("jobdetails"),data.get("location"));
		if(searchResult.verifyResults()) {
			report().report(LogStatus.PASS, "Verify Result", "Job Search Result displayed as expected");
		}else {
			report().report(LogStatus.FAIL, "Verify Result", "Job Search Result not displayed",true);
		}
	}
	
	
	
}
