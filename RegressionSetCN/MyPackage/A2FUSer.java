package MyPackage;


import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jxl.Workbook;
import jxl.read.biff.BiffException;

public class A2FUSer extends CNBase {

	@BeforeTest
	public static void Recording() throws Exception{
		VideoRecording.startRecording();
	}
    @AfterTest
    public static void StopRecording1() throws Exception{
		VideoRecording.stopRecording();
	}
	
	@Test  (priority = 0)
	public static void Login() throws BiffException, IOException, Exception  {
		 
	
		System.setProperty("webdriver.chrome.driver", "properties/chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		driver.manage().deleteAllCookies();
		baseUrl3 =  wb.getSheet("A2FLogindetails").getCell(2,1).getContents();
		driver.get(baseUrl3);
	    int rowcount = wb.getSheet("A2FLogindetails").getRows();
		
		for (i=1;i<rowcount;i++ ){
			String data00 = wb.getSheet("A2FLogindetails").getCell(0,i).getContents();
			String data01 = wb.getSheet("A2FLogindetails").getCell(1,i).getContents();
            WebElement username = driver.findElement(By.id("username"));
			username.sendKeys(data00);
			HighlightElement.elementHighlight(username);
			WebElement password = driver.findElement(By.id("password"));
			password.sendKeys(data01);
			HighlightElement.elementHighlight(password);
			WebElement Login= driver.findElement(By.id("Login"));
			HighlightElement.elementHighlight(Login);
			Login.click();
			System.out.println("Login SuccessFully");
			Thread.sleep(5000);
			//String TakescreenShot =  GenericHelper.takeScreenShot("Login");
			//Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Login Sucessfully screenshot at " + new Date()+ "</p>");
		}
	}
	
	@Test  (priority = 1)
	public static void IterationScenarios() throws BiffException, IOException, Exception  {
		
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		DecisiononConceptNote = wb.getSheet("A2FDetails").getCell(0,i).getContents();
		GACConfirmationofTRPDecision = wb.getSheet("A2FDetails").getCell(1,i).getContents();
		Status =  wb.getSheet("A2FDetails").getCell(2,i).getContents();
		rowcount = wb.getSheet("A2FDetails").getRows();
		Thread.sleep(2000);
		for(i=1; i<=rowcount; i++){
			Edit = driver.findElement(By.name("edit"));
			Thread.sleep(2000);
			HighlightElement.elementHighlight(Edit);
			Thread.sleep(2000);
			Edit.click();
			Thread.sleep(2000);
		    DecisionDropDown = driver.findElement(By.id("00Nb0000009YlMI"));
		    DecisionDropDown.click();
		    Thread.sleep(2000);
		    Select DDD=new Select(DecisionDropDown);
		    Thread.sleep(2000);
		    DDD.selectByVisibleText(DecisiononConceptNote);
		    Thread.sleep(2000);
		    GACConfirmationDropDown = driver.findElement(By.id("00Nb0000009YlMR"));
		    GACConfirmationDropDown.click();
		    Select GACYN=new Select(GACConfirmationDropDown);
		    Thread.sleep(2000);
		    GACYN.selectByValue(GACConfirmationofTRPDecision);
		    Thread.sleep(2000);
		    driver.findElement(By.name("save")).click();
		    Thread.sleep(5000);
		    Assert.assertEquals(Status, driver.findElement(By.id("00Nb0000002BSIS_ilecell")).getText());
		}
		
	  }
		/*
	@Test  (priority = 2)
	public static void IterationNo() throws BiffException, IOException, Exception  {
		
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		DecisiononConceptNote = wb.getSheet("A2FDetails").getCell(0,2).getContents();
		GACConfirmationofTRPDecision = wb.getSheet("A2FDetails").getCell(1,2).getContents();
		Status =  wb.getSheet("A2FDetails").getCell(2,2).getContents();
		Edit = driver.findElement(By.name("edit"));
		Thread.sleep(2000);
		HighlightElement.elementHighlight(Edit);
		Thread.sleep(2000);
		Edit.click();
		Thread.sleep(2000);
	    DecisionDropDown = driver.findElement(By.id("00Nb0000009YlMI"));
	    Select DDD=new Select(DecisionDropDown);
	    DDD.selectByVisibleText(DecisiononConceptNote);
	    GACConfirmationDropDown = driver.findElement(By.id("00Nb0000009YlMR"));
	    Select GACYN=new Select(DecisionDropDown);
	    GACYN.selectByVisibleText(GACConfirmationofTRPDecision);
	    driver.findElement(By.name("save")).click();
	    Thread.sleep(2000);
	    Assert.assertEquals(Status, driver.findElement(By.id("00Nb0000002BSIS_ilecell")).getText());
	  } */
	}
		
