package core;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestReporter {

    private ExtentReports extent;
    private ExtentTest test;
    private WebDriver driver;

    public TestReporter(){
        extent = new ExtentReports("./TestReport.html", true);
        File file = new File("./ScreenShots");
        if(!file.exists()){
            file.mkdir();
        }
    }

    public void startReporting(String testName, WebDriver driver){
            this.driver =  driver;
            test = extent.startTest(testName, "");
    }

    public void endReporting(){
        extent.endTest(test);
    }

    public void flushReport(){
        extent.flush();
        extent.close();
    }

    public void report(LogStatus status, String stepName, String description){
        if(test!=null){
            test.log(status, stepName, description);
        }
    }

    public void report(LogStatus status, String stepName, String description, boolean takeSnap) throws Exception {
        if(test!=null){
            if(takeSnap){
                String snapLocation = takeSnapShot();
                test.log(status, stepName, description + " " + test.addScreenCapture(snapLocation));
            }else{
                test.log(status, stepName, description);
            }
        }
    }

    private String takeSnapShot() throws Exception{

        TakesScreenshot scrShot =((TakesScreenshot) driver);
        //Call getScreenshotAs method to create image file

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        Path srcPath = SrcFile.toPath();
        //Move image file to new destination

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        date = date.replaceAll("/","").replaceAll(":","").replaceAll(" ","");

        File DestFile=new File("ScreenShots/"+date+".png");
        Path destPath = DestFile.toPath();
        //Copy file at destination

        Files.copy(srcPath,destPath);

        return destPath.toString();

    }



}
