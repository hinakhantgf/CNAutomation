package MyPackage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CTUser extends CNBase{
	static WebDriver driver;
	static File src = new File("C:\\Users\\554525\\Desktop\\Sneha\\CNProject\\CT_Data.xls");
	static int i;
	public String handle,handle1;
	
	//Login to the application with CT user
	@Test  (priority = 0)
	public static void Login() throws BiffException, IOException, Exception  {
		 
	
		System.setProperty("webdriver.chrome.driver", "properties/chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Workbook wb = Workbook.getWorkbook(src);
		int rowcount = wb.getSheet("CTLogin").getRows();
		
		for (i=1;i<rowcount;i++ ){
			String data00 = wb.getSheet("CTLogin").getCell(0,i).getContents();
			String data01 = wb.getSheet("CTLogin").getCell(1,i).getContents();
			driver.get(baseUrl2);
			//Enter username in login page
			WebElement username = driver.findElement(By.id("username"));
			HighlightElement.elementHighlight(username);
			
			username.sendKeys(data00);
			//Enter password in login page
			WebElement password = driver.findElement(By.id("password"));
			HighlightElement.elementHighlight(password);
			
			password.sendKeys(data01);
			WebElement Login= driver.findElement(By.id("Login"));
			Login.click();
			System.out.println("Login SuccessFully");
			
			//String TakescreenShot =  GenericHelper.takeScreenShot("Login");
			//Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Login Sucessfully screenshot at " + new Date()+ "</p>");
		}
	
	}	
	
	
	//Verify CT user is able to edit details of CN with status as Submitted to Global Fund.
	
		@Test  (priority = 1)
		public static void CTEditChk() throws BiffException, IOException, Exception  {
			
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			Record = wb.getSheet("CTEditRecord").getCell(0,1).getContents();
			boolean exists;
			//WebElement CN=driver.findElement(By.id("tsidLabel"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//CN.click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
			
			driver.findElement(By.linkText("Concept Notes")).click();
			
			driver.findElement(By.linkText(Record)).click();
			//Click on GOALS & IMPACT INDICATORS link
			WebElement impact = driver.findElement(By.linkText("GOALS & IMPACT INDICATORS"));
			HighlightElement.elementHighlight(impact);
			impact.click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
			//Check if Add Goal link is present on the page
			exists= isElementPresent("Add Goal");
			Assert.assertTrue(exists);
			//Check if Add Custom Indicator link is present on the page
			exists= isElementPresent("Add Custom Indicator");
			Assert.assertTrue(exists);
			//Check if Add Standard Indicator link is present on the page
			exists= isElementPresent("Add Standard Indicator");
			Assert.assertTrue(exists);
	}	
		//Function to check element is present on the page
		public static boolean isElementPresent (String id)
		{
			boolean present;
			if(driver.findElements(By.linkText(id)).size()!=0)
			{
			 present = true;
			}
			else
			{
			 present = false;
			}
		return present;
		}
	
	//Verify CT can view Assess Likelihood button and able to click on pretty sure radio button for CN with status as "Submitted to Global Fund"
		@Test  (priority = 2)
		public static void CTPositiveAssessment() throws BiffException, IOException, Exception  {
			
			Workbook wb = Workbook.getWorkbook(src);
			String data00= wb.getSheet("LikelihoodRecord").getCell(0,1).getContents();
			//WebElement CN=driver.findElement(By.id("tsidLabel"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//CN.click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			driver.findElement(By.linkText("Concept Notes")).click();
			
			driver.findElement(By.linkText(data00)).click();
			
			//Click on Submit Concept Note button
			WebElement assess = driver.findElement(By.id("CNoverview:frm:CommandLinkAssess"));
			HighlightElement.elementHighlight(assess);
			
			assess.click();
			//Click on pretty sure radio button
			WebElement radio1= driver.findElement(By.id("CNoverview:frm:probNotRadio1:0"));
			HighlightElement.elementHighlight(radio1);
			
			radio1.click();
			//Click on confirm button
			WebElement confirm= driver.findElement(By.id("CNoverview:frm:j_id108"));
			HighlightElement.elementHighlight(confirm);
			
			confirm.click();
		}
	
		
	//Verify CT can view Assess Likelihood button and able to click on not sure radio button for CN with status as "Submitted to Global Fund"
			@Test  (priority = 3)
			public static void CTNegativeAssessment() throws BiffException, IOException, Exception  {
					
			Workbook wb = Workbook.getWorkbook(src);
			String data00= wb.getSheet("LikelihoodRecord").getCell(0,2).getContents();
			String data01= wb.getSheet("Assessment").getCell(0,1).getContents();
			
			//WebElement CN=driver.findElement(By.id("tsidLabel"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//CN.click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
			driver.findElement(By.linkText("Concept Notes")).click();
			
			driver.findElement(By.linkText(data00)).click();
			
			//Click on Submit Concept Note button
			WebElement assess = driver.findElement(By.id("CNoverview:frm:CommandLinkAssess"));
			HighlightElement.elementHighlight(assess);
			
			assess.click();
			//Click on probably not radio button
			WebElement radio1= driver.findElement(By.id("CNoverview:frm:probNotRadio1:1"));
			HighlightElement.elementHighlight(radio1);
			
			radio1.click();
			//Enter the most realistic submission date
			WebElement date= driver.findElement(By.id("CNoverview:frm:subDateListAssess"));
			HighlightElement.elementHighlight(date);
			
			date.sendKeys(data01);
			//Click on confirm button
			WebElement confirm= driver.findElement(By.id("CNoverview:frm:j_id108"));
			HighlightElement.elementHighlight(confirm);
			
			confirm.click();
		
		}
								
	
	//CT is able to view the Final CT Review button and click it for CN with status as "Submitted to Global Fund"
	
			@Test  (priority = 4)
			public static void FinalCTReview() throws BiffException, IOException, Exception  {
	
			Workbook wb = Workbook.getWorkbook(src);
			String data00= wb.getSheet("CTReviewRecord").getCell(0,1).getContents();

			//WebElement CN=driver.findElement(By.id("tsidLabel"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//CN.click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
			driver.findElement(By.linkText("Concept Notes")).click();
			WebElement record=driver.findElement(By.linkText(data00));
			HighlightElement.elementHighlight(record);
			record.click();
			//Click on Final CT Review button
			WebElement FinalReview = driver.findElement(By.linkText("Final CT Review"));
			HighlightElement.elementHighlight(FinalReview);
			FinalReview.click();
			
			//Click on the checkbox to confirm the review process
			WebElement ChkBox = driver.findElement(By.id("CNoverview:frm:checkBox"));
			HighlightElement.elementHighlight(ChkBox);
			ChkBox.click();
			
			//Click on Final CT Review button
			WebElement FinalCTReviewBtn = driver.findElement(By.id("CNoverview:frm:finishCTR"));
			HighlightElement.elementHighlight(FinalCTReviewBtn);
			FinalCTReviewBtn.click();
			
			//Click on Confirm button
			WebElement ConfirmBtn =	driver.findElement(By.name("CNoverview:frm:j_id89"));
			HighlightElement.elementHighlight(ConfirmBtn);
			ConfirmBtn.click();
			}

	

	//CT upload the final translated document- Translation Upload button for CN with status as "Reviewed and OK for TRP/GAC1"
			@Test  (priority = 5)
			public static void FinalTranslationReview() throws BiffException, IOException, Exception  {

				Workbook wb = Workbook.getWorkbook(src);
				String data00= wb.getSheet("FinalTranslationRecord").getCell(0,1).getContents();

				//WebElement CN=driver.findElement(By.id("tsidLabel"));
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				//CN.click();
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
				
				driver.findElement(By.linkText("Concept Notes")).click();
				WebElement record=driver.findElement(By.linkText(data00));
				HighlightElement.elementHighlight(record);
				record.click();
				//Click on Manage Documents Link
				WebElement ManageDocs = driver.findElement(By.linkText("MANAGE DOCUMENTS"));
				HighlightElement.elementHighlight(ManageDocs);
				ManageDocs.click();
				//Click on Final Translation Review button
				WebElement FinalTranslationBtn = driver.findElement(By.linkText("Final Translation Review"));
				HighlightElement.elementHighlight(FinalTranslationBtn);
				FinalTranslationBtn.click();
				//Click on Confirm button
				WebElement ConfirmBtn = driver.findElement(By.xpath("//span[2]/a/strong"));
				HighlightElement.elementHighlight(ConfirmBtn );
				ConfirmBtn .click();
			}
}

	//Login with CCM and Verify that he is not able to edit the CN

