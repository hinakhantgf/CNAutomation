package MyPackage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

//import static com.lazerycode.selenium.DriverFactory.getDriver;

public class TestListener extends TestListenerAdapter {
	 private boolean createFile(File screenshot) {
	        boolean fileCreated = false;

	        if (screenshot.exists()) {
	            fileCreated = true;
	        } else {
	            File parentDirectory = new File(screenshot.getParent());
	            if (parentDirectory.exists() || parentDirectory.mkdirs()) {
	                try {
	                    fileCreated = screenshot.createNewFile();
	                } catch (IOException errorCreatingScreenshot) {
	                    errorCreatingScreenshot.printStackTrace();
	                }
	            }
	        }

	        return fileCreated;
	    }

	    private void writeScreenshotToFile(WebDriver driver, File screenshot) {
	        try {
	            FileOutputStream screenshotStream = new FileOutputStream(screenshot);
	            screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
	            screenshotStream.close();
	        } catch (IOException unableToWriteScreenshot) {
	            System.err.println("Unable to write " + screenshot.getAbsolutePath());
	            unableToWriteScreenshot.printStackTrace();
	        }
	    }

	    @Override
	    public  void onTestFailure(ITestResult failingTest) {
	        try {
	            //WebDriver driver = getDriver();
	        	// screenshotDirectory = "C:\\Users\\499290\\Desktop\\TF\\screenshot";
	            String screenshotAbsolutePath = CNBase.screenshotDirectory + File.separator  + "_" + failingTest.getName() + ".png";
	            File screenshot = new File(screenshotAbsolutePath);
	            if (createFile(screenshot)) {
	                try {
	                    writeScreenshotToFile(CNBase.driver, screenshot);
	                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
	                    writeScreenshotToFile(new Augmenter().augment(CNBase.driver), screenshot);
	                }
	                Reporter.log("<a href=\"" + screenshotAbsolutePath + "\"><p align=\"left\"> screenshot at " + new Date()+ "</p>");

	            } else {
	                System.err.println("Unable to create " + screenshotAbsolutePath);
	            }
	        } catch (Exception ex) {
	            System.err.println("Unable to capture screenshot...");
	            ex.printStackTrace();
	        }
	    }
	    
	    @Override
	    public void onTestSuccess(ITestResult PassTest) {
	        try {
	            //WebDriver driver = getDriver();
	            //String screenshotDirectory = "C:\\Users\\499290\\Desktop\\TF\\screenshot";
	            String screenshotAbsolutePath = CNBase.screenshotDirectory + File.separator + "_" + PassTest.getName() + ".png";
	            File screenshot = new File(screenshotAbsolutePath);
	            if (createFile(screenshot)) {
	                try {
	                    writeScreenshotToFile(CNBase.driver, screenshot);
	                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
	                    writeScreenshotToFile(new Augmenter().augment(CNBase.driver), screenshot);
	                }
	                Reporter.log("<a href=\"" + screenshotAbsolutePath + "\"><p align=\"left\"> screenshot at " + new Date()+ "</p>");

	            } else {
	                System.err.println("Unable to create " + screenshotAbsolutePath);
	            }
	        } catch (Exception ex) {
	            System.err.println("Unable to capture screenshot...");
	            ex.printStackTrace();
	        }  
	}
}