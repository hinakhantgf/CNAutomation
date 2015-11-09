package MyPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Iteration extends CNBase {
	
	@Test  (priority = 0)
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
	
	DecisiononConceptNote = wb.getSheet("A2FDetails").getCell(0,1).getContents();
    GACConfirmationofTRPDecision = wb.getSheet("A2FDetails").getCell(1,1).getContents();
    Status =  wb.getSheet("A2FDetails").getCell(2,1).getContents();
	
	
		WebElement Edit = driver.findElement(By.name("edit"));
		
		HighlightElement.elementHighlight(Edit);
		
		Edit.click();
		
		WebElement DecisionDropDown = driver.findElement(By.id("00Nb0000009YlMI"));
		DecisionDropDown.click();
		
		Select DDD=new Select(DecisionDropDown);
		
		DDD.selectByVisibleText(DecisiononConceptNote);
	    
		WebElement GACConfirmationDropDown = driver.findElement(By.id("00Nb0000009YlMR"));
		GACConfirmationDropDown.click();
		Select GACYN=new Select(GACConfirmationDropDown);
		
		GACYN.selectByValue(GACConfirmationofTRPDecision);
	    
		driver.findElement(By.name("save")).click();
		
	    Assert.assertEquals(Status, driver.findElement(By.id("00Nb0000002BSIS_ilecell")).getText());
	    System.out.println("Test Iteration scenario By A2f is passed");
		}
	

//Verify that the CCM is able to edit the CN having status as Iteration-not yet Submitted
		@Test  (priority = 1)
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
					
				
				
			Record = wb.getSheet("ItrNotYetSubmittedRecord").getCell(0,1).getContents();
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

//Verify that the CCM is able to Submit  the CN having status as Iteration-not yet Submitted
		@Test  (priority = 2)
		public static void SubmitItrCN() throws BiffException, IOException, Exception  {

			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			Record = wb.getSheet("ItrNotYetSubmittedRecord").getCell(0,1).getContents();
			//WebElement CN=driver.findElement(By.id("tsidLabel"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//CN.click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
			
			driver.findElement(By.linkText("Concept Notes")).click();
			
			driver.findElement(By.linkText(Record)).click();
			
			WebElement submit = driver.findElement(By.linkText("Submit Concept Note"));
			
			HighlightElement.elementHighlight(submit);
			
			submit.click();
			
			//Click on yes button to confirm CN submission
			WebElement confirm = driver.findElement(By.id("CNoverview:frm:j_id57"));
			
			HighlightElement.elementHighlight(confirm);
			
			confirm.click();
			

		
}

}
