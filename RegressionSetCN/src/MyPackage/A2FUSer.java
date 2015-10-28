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
		driver.get(baseUrl2);
	    int rowcount = wb.getSheet("A2FLogin").getRows();
		
		for (i=1;i<rowcount;i++ ){
			String data00 = wb.getSheet("A2FLogin").getCell(0,i).getContents();
			String data01 = wb.getSheet("A2FLogin").getCell(1,i).getContents();
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
	public static void ClickAllTabs() throws BiffException, IOException, Exception  {
		//Click on All Tabs "+" sign
		WebElement AllTabs = driver.findElement(By.cssSelector("img.allTabsArrow"));
		Thread.sleep(1000);
		HighlightElement.elementHighlight(AllTabs);
		Thread.sleep(1000);
		
		
	}
		
	@Test  (priority = 2)
	public static void ClickConceptNoteTab() throws BiffException, IOException, Exception  {
	}
}