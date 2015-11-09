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

public class CNRegressionAutomation extends CNBase {
//login before checkout
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
			Thread.sleep(5000);
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
		 //String TakescreenShot =  GenericHelper.takeScreenShot(driver, "VerificationOfOverviewPage");
		 
		 //Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Messages are verified screenshot at " + new Date()+ "</p>");
		// GenericHelper.takeScreenShot("VerificationOfOverviewPage.");
		       
  }
  
	
 
  
 
 
//Verify CCM user is able to submit CN by clicking on Submit Concept Note button

	@Test  (priority = 18)
	public static void SubmitCN() throws BiffException, IOException, Exception  {
		
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		Record = wb.getSheet("Record").getCell(0,1).getContents();
		//WebElement CN=driver.findElement(By.id("tsidLabel"));
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//CN.click();
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
		driver.findElement(By.linkText("Concept Notes")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText(Record)).click();
		//Click on Submit Concept Note button
		WebElement submit = driver.findElement(By.linkText("Submit Concept Note"));
		Thread.sleep(1000);
		HighlightElement.elementHighlight(submit);
		Thread.sleep(2000);
		submit.click();
		//Click on yes button to confirm CN submission
		WebElement confirm = driver.findElement(By.id("CNoverview:frm:j_id57"));
		HighlightElement.elementHighlight(confirm);
		Thread.sleep(2000);
		confirm.click();
		Thread.sleep(5000);

	}

//Verify CCM user is not able to edit details after concept note is submitted.
	
	@Test  (priority = 19)
	public static void CCMEditChk() throws BiffException, IOException, Exception  {
		
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		Record = wb.getSheet("Record").getCell(0,1).getContents();
		boolean exists;
		//WebElement CN=driver.findElement(By.id("tsidLabel"));
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//CN.click();
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		Thread.sleep(1000);
		driver.findElement(By.linkText("Concept Notes")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText(Record)).click();
		//Click on GOALS & IMPACT INDICATORS link
		WebElement impact = driver.findElement(By.linkText("GOALS & IMPACT INDICATORS"));
		HighlightElement.elementHighlight(impact);
		impact.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		//Check if Add Goal link is present on the page
		exists= isElementPresent("Add Goal");
		Assert.assertFalse(exists);
		//Check if Add Custom Indicator link is present on the page
		exists= isElementPresent("Add Custom Indicator");
		Assert.assertFalse(exists);
		//Check if Add Standard Indicator link is present on the page
		exists= isElementPresent("Add Standard Indicator");
		Assert.assertFalse(exists);
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

	@Test  (priority = 20)
	public static void CTLogin() throws BiffException, IOException, Exception  {
		 
	
		System.setProperty("webdriver.chrome.driver", "properties/chromedriver.exe");
		//driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		int rowcount = wb.getSheet("CTLogin").getRows();
		
		for (i=1;i<rowcount;i++ ){
			String data00 = wb.getSheet("CTLogin").getCell(0,i).getContents();
			String data01 = wb.getSheet("CTLogin").getCell(1,i).getContents();
			driver.get(baseUrl2);
			//Enter username in login page
			WebElement username = driver.findElement(By.id("username"));
			HighlightElement.elementHighlight(username);
			Thread.sleep(2000);
			username.sendKeys(data00);
			//Enter password in login page
			WebElement password = driver.findElement(By.id("password"));
			HighlightElement.elementHighlight(password);
			Thread.sleep(2000);
			password.sendKeys(data01);
			WebElement Login= driver.findElement(By.id("Login"));
			Login.click();
			System.out.println("Login SuccessFully");
			Thread.sleep(5000);
			//String TakescreenShot =  GenericHelper.takeScreenShot("Login");
			//Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Login Sucessfully screenshot at " + new Date()+ "</p>");
		}
	
	}		
	
	@Test (priority = 21)
	public static void FinalCoreDocument() throws BiffException, IOException, InterruptedException{
		   
		    Workbook wb = Workbook.getWorkbook(srcCNRegression);
		    String Record= wb.getSheet("CTReviewRecord").getCell(0,1).getContents();
		    Thread.sleep(5000);
		    driver.findElement(By.linkText("Concept Notes")).click();
		    Thread.sleep(5000);
			WebElement record=driver.findElement(By.linkText(Record));
			Thread.sleep(2000);
			record.click();
			Thread.sleep(2000);
		    driver.findElement(By.linkText("Manage Documents")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.id("page:frm:rpt:0:j_id160")).click();
		    Thread.sleep(5000);
		  }
	
	
	
	//CT is able to view the Final CT Review button and click it for CN with status as "Submitted to Global Fund"
	
			@Test  (priority = 22)
			public static void FinalCTReview() throws BiffException, IOException, Exception  {
	
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			String Record= wb.getSheet("CTReviewRecord").getCell(0,1).getContents();

			//WebElement CN=driver.findElement(By.id("tsidLabel"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//CN.click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
			driver.findElement(By.linkText("Concept Notes")).click();
			WebElement record=driver.findElement(By.linkText(Record));
			//Thread.sleep(5000);
			//HighlightElement.elementHighlight(record);
			Thread.sleep(2000);
			record.click();
			//Click on Final CT Review button
			WebElement FinalReview = driver.findElement(By.linkText("Final CT Review"));
			Thread.sleep(2000);
			HighlightElement.elementHighlight(FinalReview);
			Thread.sleep(2000);
			FinalReview.click();
			Thread.sleep(2000);
			//Click on the checkbox to confirm the review process
			WebElement ChkBox = driver.findElement(By.id("CNoverview:frm:checkBox"));
			Thread.sleep(2000);
			HighlightElement.elementHighlight(ChkBox);
			Thread.sleep(2000);
			ChkBox.click();
			//Click on Final CT Review button
			WebElement FinalCTReviewBtn = driver.findElement(By.id("CNoverview:frm:finishCTR"));
			Thread.sleep(2000);
			HighlightElement.elementHighlight(FinalCTReviewBtn);
			Thread.sleep(2000);
			FinalCTReviewBtn.click();
			Thread.sleep(2000);
			//Click on Confirm button
			WebElement ConfirmBtn =	driver.findElement(By.name("CNoverview:frm:j_id89"));
			Thread.sleep(2000);
			HighlightElement.elementHighlight(ConfirmBtn);
			Thread.sleep(2000);
			ConfirmBtn.click();
			Thread.sleep(2000);
	}

	

	//CT upload the final translated document- Translation Upload button for CN with status as "Reviewed and OK for TRP/GAC1"
			@Test  (priority = 23)
			public static void FinalTranslationReview() throws BiffException, IOException, Exception  {

				Workbook wb = Workbook.getWorkbook(srcCNRegression);
				String data00= wb.getSheet("FinalTranslationRecord").getCell(0,1).getContents();

				//WebElement CN=driver.findElement(By.id("tsidLabel"));
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				//CN.click();
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
				Thread.sleep(6000);
				driver.findElement(By.linkText("Concept Notes")).click();
				Thread.sleep(5000);
				WebElement record=driver.findElement(By.linkText(data00));
				Thread.sleep(5000);
				HighlightElement.elementHighlight(record);
				record.click();
				Thread.sleep(5000);
				//Click on Manage Documents Link
				WebElement ManageDocs = driver.findElement(By.linkText("MANAGE DOCUMENTS"));
				Thread.sleep(5000);
				HighlightElement.elementHighlight(ManageDocs);
				Thread.sleep(5000);
				ManageDocs.click();
				Thread.sleep(5000);
				WebElement FinalTranslationBtn = driver.findElement(By.linkText("Final Translation Review"));
				Thread.sleep(5000);
				HighlightElement.elementHighlight(FinalTranslationBtn);
				Thread.sleep(5000);
				FinalTranslationBtn.click();
				Thread.sleep(5000);
				//Click on Confirm button
				WebElement ConfirmBtn = driver.findElement(By.xpath("//span[2]/a/strong"));
				Thread.sleep(5000);
				HighlightElement.elementHighlight(ConfirmBtn );
				Thread.sleep(5000);
				ConfirmBtn.click();
			}

			@Test  (priority = 24)
			public static void CCMEditChkAfterCTReview() throws BiffException, IOException, Exception  {
					
				   Workbook wb = Workbook.getWorkbook(srcCNRegression);
				   driver.get(baseUrl1);
					String data00 = wb.getSheet("Login").getCell(0,1).getContents();
					String data01 = wb.getSheet("Login").getCell(1,1).getContents();
		            WebElement username = driver.findElement(By.id("username"));
		            System.out.println(data00 + "User name Pls Login with CCM");
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
				Record = wb.getSheet("Record").getCell(0,1).getContents();
				boolean exists;
				//WebElement CN=driver.findElement(By.id("tsidLabel"));
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				//CN.click();
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
				Thread.sleep(1000);
				driver.findElement(By.linkText("Concept Notes")).click();
				Thread.sleep(5000);
				driver.findElement(By.linkText(Record)).click();
				//Click on GOALS & IMPACT INDICATORS link
				WebElement impact = driver.findElement(By.linkText("GOALS & IMPACT INDICATORS"));
				HighlightElement.elementHighlight(impact);
				impact.click();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
				//Check if Add Goal link is present on the page
				exists= isElementPresent("Add Goal");
				Assert.assertFalse(exists);
				//Check if Add Custom Indicator link is present on the page
				exists= isElementPresent("Add Custom Indicator");
				Assert.assertFalse(exists);
				//Check if Add Standard Indicator link is present on the page
				exists= isElementPresent("Add Standard Indicator");
				Assert.assertFalse(exists);
				System.out.println("Test 24 Is passed");
				}
				
				@Test  (priority = 25)
				public static void IterationScenarios() throws BiffException, IOException, Exception  {

				Workbook wb = Workbook.getWorkbook(srcCNRegression);
				baseUrl3 =  wb.getSheet("A2FLogindetails").getCell(2,1).getContents();
				driver.get(baseUrl3);
				String data00 = wb.getSheet("A2FLogindetails").getCell(0,1).getContents();
				String data01 = wb.getSheet("A2FLogindetails").getCell(1,1).getContents();
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
				DecisiononConceptNote = wb.getSheet("A2FDetails").getCell(0,1).getContents();
			    GACConfirmationofTRPDecision = wb.getSheet("A2FDetails").getCell(1,1).getContents();
			    Status =  wb.getSheet("A2FDetails").getCell(2,i).getContents();
				rowcount = wb.getSheet("A2FDetails").getRows();
				Thread.sleep(2000);
				for(i=1; i<=rowcount; i++){
					WebElement Edit = driver.findElement(By.name("edit"));
					Thread.sleep(2000);
					HighlightElement.elementHighlight(Edit);
					Thread.sleep(2000);
					Edit.click();
					Thread.sleep(2000);
					WebElement DecisionDropDown = driver.findElement(By.id("00Nb0000009YlMI"));
					DecisionDropDown.click();
					Thread.sleep(2000);
					Select DDD=new Select(DecisionDropDown);
					Thread.sleep(2000);
					DDD.selectByVisibleText(DecisiononConceptNote);
				    Thread.sleep(2000);
					WebElement GACConfirmationDropDown = driver.findElement(By.id("00Nb0000009YlMR"));
					GACConfirmationDropDown.click();
					Select GACYN=new Select(GACConfirmationDropDown);
					Thread.sleep(2000);
					GACYN.selectByValue(GACConfirmationofTRPDecision);
				    Thread.sleep(2000);
					driver.findElement(By.name("save")).click();
					Thread.sleep(2000);
				    Assert.assertEquals(Status, driver.findElement(By.id("00Nb0000002BSIS_ilecell")).getText());
				    System.out.println("Test Iteration scenario By A2f is passed");
					}
				}

			//Verify that the CCM is able to edit the CN having status as Iteration-not yet Submitted
					@Test  (priority = 26)
					public static void ItrNotSubmittedEditChk() throws BiffException, IOException, Exception  {

						Workbook wb = Workbook.getWorkbook(srcCNRegression);
						driver.get(baseUrl1);
								String data00 = wb.getSheet("Login").getCell(0,1).getContents();
								String data01 = wb.getSheet("Login").getCell(1,1).getContents();
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
							
							
						Record = wb.getSheet("ItrNotYetSubmittedRecord").getCell(0,1).getContents();
						boolean exists;
						//WebElement CN=driver.findElement(By.id("tsidLabel"));
						//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
						//CN.click();
						//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
						//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
						//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
						Thread.sleep(1000);
						driver.findElement(By.linkText("Concept Notes")).click();
						Thread.sleep(5000);
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

			//Verify that the CCM is able to Submit  the CN having status as Iteration-not yet Submitted
					@Test  (priority = 27)
					public static void SubmitItrCN() throws BiffException, IOException, Exception  {

						Workbook wb = Workbook.getWorkbook(srcCNRegression);
						Record = wb.getSheet("ItrNotYetSubmittedRecord").getCell(0,1).getContents();
						//WebElement CN=driver.findElement(By.id("tsidLabel"));
						//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
						//CN.click();
						//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
						//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
						//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
						Thread.sleep(5000);
						driver.findElement(By.linkText("Concept Notes")).click();
						Thread.sleep(5000);
						driver.findElement(By.linkText(Record)).click();
						Thread.sleep(1000);
						WebElement submit = driver.findElement(By.linkText("Submit Concept Note"));
						Thread.sleep(1000);
						HighlightElement.elementHighlight(submit);
						Thread.sleep(2000);
						submit.click();
						Thread.sleep(1000);
						//Click on yes button to confirm CN submission
						WebElement confirm = driver.findElement(By.id("CNoverview:frm:j_id57"));
						Thread.sleep(1000);
						HighlightElement.elementHighlight(confirm);
						Thread.sleep(2000);
						confirm.click();
						Thread.sleep(1000);

					
    }

}
	
	

