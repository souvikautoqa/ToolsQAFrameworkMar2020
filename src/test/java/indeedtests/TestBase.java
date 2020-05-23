package indeedtests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import core.DriverFactory;
import core.ExcelDataProvider;
import core.ReadConfig;
import core.TestReporter;

public class TestBase {
	
	private DriverFactory factory;
	private WebDriver driver;
	//private String env = System.getenv("env");
	private String env = "int";
	private ExcelDataProvider excel;
	String excelDataFileLoc = System.getProperty("user.dir")+"//src//test//resources//data//TestData.xlsx";
	private TestReporter report;
	
	@BeforeSuite
	public void initSuite() throws Exception {
		ReadConfig.loadProperty(env);
		report = new TestReporter();
	}
	
	@BeforeClass
	public void initBrowser() throws Exception {
		factory = new DriverFactory();
		factory.initDriver(ReadConfig.getProperty("browser"));
		driver = factory.driver();
		excel = new ExcelDataProvider(excelDataFileLoc,env.toUpperCase());
	}
	
	@BeforeMethod
	public void lauchApp(Method method) {
		driver.get(ReadConfig.getProperty("URL"));
		report.startReporting(method.getName(), driver);
	}
	
	@AfterMethod
	public void endReport() {
		report.endReporting();
	}
	
	@AfterSuite
	public void flushReport() {
		report.flushReport();
	}
	
	public TestReporter report() {
		return report;
	}
	
	public WebDriver driver() {
		return driver;
	}
	
	@DataProvider
	public Object[][] getData(Method method) {
		List<HashMap<String,String>> data = excel.getAllData(method.getName());
		int rowNo = data.size();
		Object[][] dataArray = new Object[rowNo][1];
		for(int i=0;i<rowNo;i++) {
			dataArray[i][0] = data.get(i);
		}
		return dataArray;
	}
		
	@AfterClass
	public void quitDriver() {
		factory.killDriver();
	}
	

}
