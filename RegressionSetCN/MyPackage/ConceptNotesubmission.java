package MyPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConceptNotesubmission extends CNBase{

	@Test  (priority = 0)
	public static void Login() throws BiffException, IOException, Exception  {
		 
	
		System.setProperty("webdriver.chrome.driver", "properties/chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.get(baseUrl1);
	    int rowcount = wb.getSheet("Login").getRows();
		
		for (i=1;i<rowcount;i++ ){
			String data00 = wb.getSheet("Login").getCell(0,i).getContents();
			String data01 = wb.getSheet("Login").getCell(1,i).getContents();
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
			
			//String TakescreenShot =  GenericHelper.takeScreenShot("Login");
			//Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Login Sucessfully screenshot at " + new Date()+ "</p>");
		}
	}
	
	
	@Test (priority = 1)
    public void VerificationOfOverviewPage() throws Exception {
	  
    	 
    	 //System.setProperty("webdriver.chrome.driver", "properties/chromedriver.exe");
		// driver = new ChromeDriver();
	     Workbook wb = Workbook.getWorkbook(srcCNRegression);
		 String Msg1 = wb.getSheet("OverviewPage").getCell(0,1).getContents();
		 String Msg2 = wb.getSheet("OverviewPage").getCell(0,2).getContents();
		 String Msg3 = wb.getSheet("OverviewPage").getCell(0,3).getContents();
		 String Msg4 = wb.getSheet("OverviewPage").getCell(0,4).getContents();
	 	 System.out.println(Msg1);
	 	 System.out.println(Msg2);
	 	 System.out.println(Msg3);
	 	 System.out.println(Msg4);
	 	Assert.assertEquals(Msg1, driver.getTitle());
         System.out.println("Verified the page title");
         
         try {
        	 Assert.assertEquals(Msg2, driver.findElement(By.cssSelector("#Infopanel > b")).getText());
			  System.out.println("Verified the first Alert Message Of home Page");
			  //System.out.println(driver.getTitle());
				} catch (Exception e) {
                  System.out.println("Print if  value  not match" );
				verificationErrors.append(e.toString());
				}
				try {
					Assert.assertEquals(Msg3, driver.findElement(By.linkText("See Help")).getText());
			        System.out.println("Verified the See Help link Text Of home Page");
			  } catch (Error e) {
			    verificationErrors.append(e.toString());
			  }
				
				Assert.assertEquals(Msg4, driver.findElement(By.xpath("//form[@id='page1:frm']/div/div[4]")).getText());
		 System.out.println("Verified the second alert message Of home Page");
		 System.out.println("OverView Page is verified.");
		 //String TakescreenShot =  GenericHelper.takeScreenShot(driver, "VerificationOfOverviewPage");
		 
		 //Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Messages are verified screenshot at " + new Date()+ "</p>");
		// GenericHelper.takeScreenShot("VerificationOfOverviewPage.");
		       
  }
  
	
	@Test (priority = 2)
	 public void TRPDateRegister() throws Exception {
			
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			//driver.findElement(By.linkText("Concept Notes")).click();
			Record = wb.getSheet("TRPRegisterRecord").getCell(0,1).getContents();
			String TRPDate = wb.getSheet("TRPRegisterRecord").getCell(1,1).getContents();
			String Langauge = wb.getSheet("TRPRegisterRecord").getCell(2,1).getContents();
			RecordLink = driver.findElement(By.linkText(Record));
			RecordLink.click();
			Thread.sleep(1000);
			WebElement TRPButton = driver.findElement(By.linkText("Register to TRP"));
		    HighlightElement.elementHighlight(TRPButton);
		    TRPButton.click();
		    new Select(driver.findElement(By.id("CNoverview:frm:subDateList"))).selectByVisibleText(TRPDate);
		    new Select(driver.findElement(By.id("CNoverview:frm:subLang"))).selectByVisibleText(Langauge);
		    WebElement Save = driver.findElement(By.id("CNoverview:frm:j_id118"));
		    Thread.sleep(1000);
		    HighlightElement.elementHighlight(Save);
		    Thread.sleep(1000);
		    Save.click();
		    System.out.println(" TRP Date Regoster is done");
    
   }
	 
	//Verify CT can view Assess Likelihood button and able to click on pretty sure radio button for CN with status as "Submitted to Global Fund"
			@Test  (priority = 3)
			public static void CTPositiveAssessment() throws BiffException, IOException, Exception  {
				
				Workbook wb = Workbook.getWorkbook(srcCNRegression);
				String data00= wb.getSheet("LikelihoodRecord").getCell(0,1).getContents();
					String UserName = wb.getSheet("CTLogin").getCell(0,1).getContents();
					String PWD = wb.getSheet("CTLogin").getCell(1,1).getContents();
					driver.get(baseUrl2);
					//Enter username in login page
					WebElement username = driver.findElement(By.id("username"));
					HighlightElement.elementHighlight(username);
					
					username.sendKeys(UserName);
					//Enter password in login page
					WebElement password = driver.findElement(By.id("password"));
					HighlightElement.elementHighlight(password);
					
					password.sendKeys(PWD);
					WebElement Login= driver.findElement(By.id("Login"));
					Login.click();
					System.out.println("Login SuccessFully");
					
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
				 System.out.println(" TRP Date Regoster is done and CT is logged in to do the assesment");
				
			}
							
		
			 @Test (priority = 4)
				
				public void ChangeSubmissionDate() throws IOException, InterruptedException, BiffException {
					
					Workbook wb = Workbook.getWorkbook(srcCNRegression);
					String DateVal = wb.getSheet("CNSubmissionForm").getCell(0, 1).getContents();
					String LanguageVal = wb.getSheet("CNSubmissionForm").getCell(1, 1).getContents();
					String JustificationVal =  wb.getSheet("CNSubmissionForm").getCell(2, 1).getContents();
					
					Record = wb.getSheet("Record").getCell(0,1).getContents();
					String data00 = wb.getSheet("Login").getCell(0,1).getContents();
					String data01 = wb.getSheet("Login").getCell(1,1).getContents();
					driver.get(baseUrl1);
		           /* WebElement username = driver.findElement(By.id("username"));
					username.sendKeys(data00);
					HighlightElement.elementHighlight(username);
					WebElement password = driver.findElement(By.id("password"));
					password.sendKeys(data01);
					HighlightElement.elementHighlight(password);
					WebElement Login= driver.findElement(By.id("Login"));
					HighlightElement.elementHighlight(Login);
					Login.click(); */
					driver.findElement(By.linkText(Record)).click();
					 
					driver.findElement(By.linkText("Change Submission Date")).click();
					
					WebElement SubmissionDate = driver.findElement(By.id("CNoverview:frm:subDateList"));
					 
					SubmissionDate.click();
					Select SubDatePick = new Select(SubmissionDate);
					
					SubDatePick.selectByValue(DateVal);
					
					WebElement SelLanguage = driver.findElement(By.id("CNoverview:frm:subLang"));
					SelLanguage.click();
					 
					Select LanguagePick = new Select(SelLanguage);
					 
					LanguagePick.selectByValue(LanguageVal);
					
					WebElement Justification = driver.findElement(By.id("CNoverview:frm:justification"));
					 
					Justification.click();
					 
					Justification.sendKeys(JustificationVal);
					
					driver.findElement(By.id("CNoverview:frm:j_id118")).click();
					 System.out.println(" submission Date is Changed");
				}

			
}
