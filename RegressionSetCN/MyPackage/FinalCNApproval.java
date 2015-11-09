package MyPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FinalCNApproval extends CNBase {
	//Verify CCM user is able to submit CN by clicking on Submit Concept Note button

		@Test  (priority = 0)
		public static void SubmitCN() throws BiffException, IOException, Exception  {
			
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			Record = wb.getSheet("Record").getCell(0,1).getContents();
			//WebElement CN=driver.findElement(By.id("tsidLabel"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//CN.click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
			
			driver.findElement(By.linkText("Concept Notes")).click();
			
			driver.findElement(By.linkText(Record)).click();
			
			//Click on Submit Concept Note button
			WebElement submit = driver.findElement(By.linkText("Submit Concept Note"));
			
			HighlightElement.elementHighlight(submit);
			
			submit.click();
			//Click on yes button to confirm CN submission
			WebElement confirm = driver.findElement(By.id("CNoverview:frm:j_id57"));
			HighlightElement.elementHighlight(confirm);
			
			confirm.click();
			

		}

	//Verify CCM user is not able to edit details after concept note is submitted.
		
		@Test  (priority = 1)
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
			
			driver.findElement(By.linkText("Concept Notes")).click();
			
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

		@Test  (priority = 2)
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
		
		@Test (priority = 3)
		public static void FinalCoreDocument() throws BiffException, IOException, InterruptedException{
			   
			    Workbook wb = Workbook.getWorkbook(srcCNRegression);
			    String Record= wb.getSheet("CTReviewRecord").getCell(0,1).getContents();
			    
			    driver.findElement(By.linkText("Concept Notes")).click();
			    
				WebElement record=driver.findElement(By.linkText(Record));
				
				record.click();
				
			    driver.findElement(By.linkText("MANAGE DOCUMENTS")).click();
			    
			    driver.findElement(By.id("page:frm:rpt:0:j_id160")).click();
			    
			  }
		
		
		
		//CT is able to view the Final CT Review button and click it for CN with status as "Submitted to Global Fund"
		
				@Test  (priority = 4)
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
				//
				//HighlightElement.elementHighlight(record);
				
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
					
					WebElement record=driver.findElement(By.linkText(data00));
					
					HighlightElement.elementHighlight(record);
					record.click();
					
					//Click on Manage Documents Link
					WebElement ManageDocs = driver.findElement(By.linkText("MANAGE DOCUMENTS"));
					
					HighlightElement.elementHighlight(ManageDocs);
					
					ManageDocs.click();
					
					WebElement FinalTranslationBtn = driver.findElement(By.linkText("Final Translation Review"));
					
					HighlightElement.elementHighlight(FinalTranslationBtn);
					
					FinalTranslationBtn.click();
					
					//Click on Confirm button
					WebElement ConfirmBtn = driver.findElement(By.xpath("//span[2]/a/strong"));
					
					HighlightElement.elementHighlight(ConfirmBtn );
					
					ConfirmBtn.click();
				}

				@Test  (priority = 6)
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
						
					Record = wb.getSheet("Record").getCell(0,1).getContents();
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
					Assert.assertFalse(exists);
					//Check if Add Custom Indicator link is present on the page
					exists= isElementPresent("Add Custom Indicator");
					Assert.assertFalse(exists);
					//Check if Add Standard Indicator link is present on the page
					exists= isElementPresent("Add Standard Indicator");
					Assert.assertFalse(exists);
					System.out.println("Test 24 Is passed");
					}
					

}
