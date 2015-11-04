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
  
	
 @Test (priority = 2)
 public void TRPDateRegister() throws Exception {
		
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		//driver.findElement(By.linkText("Concept Notes")).click();
		Record = wb.getSheet("TRPRegisterRecord").getCell(0,1).getContents();
		String TRPDate = wb.getSheet("TRPRegisterRecord").getCell(1,1).getContents();
		String Langauge = wb.getSheet("TRPRegisterRecord").getCell(2,1).getContents();
		Thread.sleep(5000);
		RecordLink = driver.findElement(By.linkText(Record));
		Thread.sleep(1000);
		RecordLink.click();
		Thread.sleep(1000);
	    WebElement TRPButton = driver.findElement(By.linkText("Register to TRP"));
	    HighlightElement.elementHighlight(TRPButton);
	    TRPButton.click();
	    Thread.sleep(1000);
	    new Select(driver.findElement(By.id("CNoverview:frm:subDateList"))).selectByVisibleText(TRPDate);
	    Thread.sleep(5000);
	    new Select(driver.findElement(By.id("CNoverview:frm:subLang"))).selectByVisibleText(Langauge);
	    Thread.sleep(1000);
	    WebElement Save = driver.findElement(By.id("CNoverview:frm:j_id118"));
	    HighlightElement.elementHighlight(Save);
	    Save.click();
	
	    
	  }
 
//Verify CT can view Assess Likelihood button and able to click on pretty sure radio button for CN with status as "Submitted to Global Fund"
		@Test  (priority = 3)
		public static void CTPositiveAssessment() throws BiffException, IOException, Exception  {
			
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			String data00= wb.getSheet("LikelihoodRecord").getCell(0,1).getContents();
			int rowcount = wb.getSheet("CTLogin").getRows();
			
			for (i=1;i<rowcount;i++ ){
				String UserName = wb.getSheet("CTLogin").getCell(0,i).getContents();
				String PWD = wb.getSheet("CTLogin").getCell(1,i).getContents();
				driver.get(baseUrl2);
				//Enter username in login page
				WebElement username = driver.findElement(By.id("username"));
				HighlightElement.elementHighlight(username);
				Thread.sleep(2000);
				username.sendKeys(UserName);
				//Enter password in login page
				WebElement password = driver.findElement(By.id("password"));
				HighlightElement.elementHighlight(password);
				Thread.sleep(2000);
				password.sendKeys(PWD);
				WebElement Login= driver.findElement(By.id("Login"));
				Login.click();
				System.out.println("Login SuccessFully");
				Thread.sleep(5000);
			//WebElement CN=driver.findElement(By.id("tsidLabel"));
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//CN.click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//driver.findElement(By.linkText("Concept Note and Grantmaking")).click();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
			driver.findElement(By.linkText("Concept Notes")).click();
			Thread.sleep(5000);
			driver.findElement(By.linkText(data00)).click();
			Thread.sleep(2000);
			//Click on Submit Concept Note button
			Thread.sleep(2000);
			WebElement assess = driver.findElement(By.id("CNoverview:frm:CommandLinkAssess"));
			Thread.sleep(2000);
			HighlightElement.elementHighlight(assess);
			Thread.sleep(2000);
			assess.click();
			Thread.sleep(2000);
			//Click on pretty sure radio button
			WebElement radio1= driver.findElement(By.id("CNoverview:frm:probNotRadio1:0"));
			Thread.sleep(2000);
			HighlightElement.elementHighlight(radio1);
			Thread.sleep(2000);
			radio1.click();
			//Click on confirm button
			WebElement confirm= driver.findElement(By.id("CNoverview:frm:j_id108"));
			HighlightElement.elementHighlight(confirm);
			Thread.sleep(2000);
			confirm.click();
		}
						
	}	
  
  @Test (priority = 4)
  public void AddNewPr() throws InterruptedException, Exception, Throwable {
	  	
      Workbook wb = Workbook.getWorkbook(srcCNRegression);
      Record = wb.getSheet("Record").getCell(0,1).getContents();
      Thread.sleep(15000);
	  driver.findElement(By.linkText(Record)).click();	
	  Thread.sleep(15000);
      int rowcount = wb.getSheet("Principal Recipients").getRows();
      /*
     if(!driver.findElement(By.id("tsidLabel")).getText().equalsIgnoreCase("Concept Note and Grantmaking")) {
 		 driver.findElement(By.id("tsidLabel")).click(); 
 		 driver.findElement(By.linkText("Concept Note and Grantmaking")).click(); */  
          for ( i=1;i<rowcount;++i){
        	  String OrgnisationName =  wb.getSheet("Principal Recipients").getCell(0,i).getContents();
			  String OrgShortName = wb.getSheet("Principal Recipients").getCell(1, i).getContents();
			  Thread.sleep(2000);
			  WebElement PRLink= driver.findElement(By.linkText("PRINCIPAL RECIPIENTS"));
			  HighlightElement.elementHighlight(PRLink);
			  PRLink.click();
			  Thread.sleep(2000);
			  driver.findElement(By.linkText("Add new PR")).click();
			  Thread.sleep(2000);
			  String handle= driver.getWindowHandle();
			   
			// Click on the modules
			  //Store and Print the name of all the windows open	 
               Set handles = driver.getWindowHandles();
               System.out.println(handles); // Pass a window handle to the other window
                      for (String handle1 : driver.getWindowHandles()) {
                    	  driver.switchTo().window(handle1);
			
	   }
		  
          WebElement OrgName= driver.findElement(By.id("j_id0:frm:pb:pbs:aName"));
		  OrgName.sendKeys(OrgnisationName);
		  Thread.sleep(2000);
		  WebElement ShortName = driver.findElement(By.id("j_id0:frm:pb:pbs:vLName"));
		  ShortName.sendKeys(OrgShortName);
		  WebElement selectPRType=driver.findElement(By.id("j_id0:frm:pb:pbs:prType"));
		  Thread.sleep(2000);
		  Select PR0=new Select(selectPRType);
		  PR0.selectByIndex(2);
		  Thread.sleep(2000);
		  driver.manage().timeouts().implicitlyWait(700, TimeUnit.SECONDS);
		  Thread.sleep(2000);
		  WebElement selectPRSubType=driver.findElement(By.id("j_id0:frm:pb:pbs:prSubType"));
		  Thread.sleep(2000);
		  Select PR2=new Select(selectPRSubType);
		  Thread.sleep(2000);
		  PR2.selectByIndex(2);
		  Thread.sleep(2000);
		  
		  Thread.sleep(2000);
		  WebElement Save=  driver.findElement(By.id("j_id0:frm:pb:j_id5:bottom:j_id6"));
		  HighlightElement.elementHighlight(Save);
		  Thread.sleep(2000);
		  Save.click();
		  Thread.sleep(2000);
		  driver.switchTo().window(handle);
				try {
				    Assert.assertEquals(OrgnisationName, driver.findElement(By.id("page:frm:rpt:0:inpGoalDec")).getText());
				System.out.println("Verified the New Added PR Name");
				  } catch (Error e) {
					  verificationErrors.append(e.toString());
					   
				  }
					   // String TakescreenShot =  GenericHelper.takeScreenShot("AddNewPr");
						//Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Add New PR screenshot at " + new Date()+ "</p>");
						System.out.println("PR is Added");  
						Thread.sleep(3000);
						
         }
          
  } 
       

  @Test (priority = 5)
  public void AddModule() throws Exception {
	  
	     Workbook wb = Workbook.getWorkbook(srcCNRegression);
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
	     Thread.sleep(5000);
	     //driver.findElement(By.linkText("Concept Notes")).click();
	     //Thread.sleep(1000);
         //Record = wb.getSheet("Record").getCell(0,1).getContents();
	     //driver.findElement(By.linkText(Record)).click();
	     //Thread.sleep(2000);
         ModuleLink = driver.findElement(By.linkText("MODULES & INTERVENTIONS"));
         Thread.sleep(5000);
		 //HighlightElement.elementHighlight(ModuleLink);
		 ModuleLink.click();
		 System.out.println(" Module link is open");
		 Thread.sleep(5000);
		 driver.findElement(By.linkText("Add Modules")).click();
		 Thread.sleep(3000);
		 // Click on the modules
		 driver.findElement(By.name("page:frm:j_id204:1:j_id206")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.name("page:frm:j_id214")).click(); 
		 Thread.sleep(3000);
	
    System.out.println("Module is added" );
			}
    
   }

  @Test  (priority =  6)
  public void AddIntervention() throws Exception {
	      
	      Workbook wb = Workbook.getWorkbook(srcCNRegression);
	      Thread.sleep(1000);
	      driver.findElement(By.linkText("Concept Notes")).click();
	      Thread.sleep(1000);
          Record = wb.getSheet("Record").getCell(0,1).getContents();
	      driver.findElement(By.linkText(Record)).click();
	      Thread.sleep(5000);
	      ModuleLink = driver.findElement(By.linkText("MODULES & INTERVENTIONS"));
	      Thread.sleep(5000);
	      HighlightElement.elementHighlight(ModuleLink);
		  Thread.sleep(5000);
		  ModuleLink.click();
		  Thread.sleep(5000);
		  driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		  Thread.sleep(5000);
		  driver.findElement(By.id("plusCommandLink1")).click();
		  Thread.sleep(5000);
		  driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		  driver.findElement(By.linkText("Add Interventions")).click();
		  Thread.sleep(5000);
		  driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		  driver.findElement(By.name("page:frm:j_id45:0:j_id171:0:j_id173")).click();
		  Thread.sleep(5000);
		  driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		  driver.findElement(By.id("page:frm:j_id45:0:j_id181")).click();
		  Thread.sleep(5000);
		  driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS); 
		  // String TakescreenShot =  GenericHelper.takeScreenShot("AddIntervention");
		 // Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Add New Intervention screenshot at " + new Date()+ "</p>");
		  System.out.println("Intervention is added" );
		  
      }

  @Test (priority = 7)
  public void AddPRintervention() throws Exception {
   
	     Thread.sleep(5000);
	     driver.findElement(By.xpath("//div[@id='childSection1']/table/tbody/tr/td[3]/a/strong")).click();
		 Thread.sleep(8000);
		 driver.findElement(By.name("page:frm:j_id45:0:j_id73:0:j_id82:0:j_id84")).click();
		 Thread.sleep(8000);
		 driver.findElement(By.id("page:frm:j_id45:0:j_id73:0:j_id90")).click();
		 Thread.sleep(5000);
		 //String TakescreenShot =  GenericHelper.takeScreenShot("AddPRintervention");
		 //Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Add PR in  Intervention screenshot at " + new Date()+ "</p>");
		 System.out.println("Intervention PR is added" );
   
       }		
  
  @Test (priority = 8)
  public void DeleteInterventionPR() throws Exception {
  	
	     Thread.sleep(5000);
	     new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[title=\"Delete\"]")));
	     Thread.sleep(5000);
	     driver.findElement(By.cssSelector("img[title=\"Delete\"]")).click();
		 Thread.sleep(5000);
		 driver.findElement(By.id("page:frm:j_id45:0:j_id73:0:j_id97:0:j_id110")).click();
		 Thread.sleep(5000);
		 //String TakescreenShot =  GenericHelper.takeScreenShot("DeleteInterventionPR");
		// Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Delete PR of Intervention screenshot at " + new Date()+ "</p>");
		 System.out.println("Intervention PR is Deleted" );
		    
  }

  @Test (priority = 9)
  public void DeleteIntervention() throws Exception {
  	     
	  
	     Thread.sleep(5000);
	     new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.linkText("MODULES & INTERVENTIONS")));
	     Thread.sleep(5000);
	     ModuleLink = driver.findElement(By.linkText("MODULES & INTERVENTIONS"));
		 HighlightElement.elementHighlight(ModuleLink);
		 Thread.sleep(5000);
		 ModuleLink.click();    
		 Thread.sleep(5000);
		 driver.findElement(By.id("plusCommandLink1")).click();
		 Thread.sleep(5000);
		 driver.findElement(By.xpath("//div[@id='childSection1']/table/tbody/tr/td[3]/a[5]/strong")).click();
		 Thread.sleep(5000);
		 driver.findElement(By.id("page:frm:j_id45:0:j_id73:0:j_id147")).click();
		 Thread.sleep(5000);
		 //String TakescreenShot =  GenericHelper.takeScreenShot("DeleteIntervention");
		 //Reporter.log("<a href=\"" + TakescreenShot + "\"><p align=\"left\">Delete Intervention screenshot at " + new Date()+ "</p>");
		 System.out.println("Intervention  is Deleted" );
		 
  }
  
  @Test (priority = 10)
  public void DeleteModule() throws Exception {
  
		 Thread.sleep(5000);
		 ModuleLink = driver.findElement(By.linkText("MODULES & INTERVENTIONS"));
		 HighlightElement.elementHighlight(ModuleLink);
		 ModuleLink.click();
		 Thread.sleep(5000);
		 driver.findElement(By.xpath("//span[@id='page:frm:ModulesPanel']/table/tbody/tr/td[4]/a[3]/strong")).click();
		 Thread.sleep(5000);
		 driver.findElement(By.id("page:frm:j_id45:0:j_id66")).click();
		 System.out.println("Module  is Deleted" );
		    
  }	  
    
 
  @Test (priority = 11)
  public void deletePR() throws InterruptedException, BiffException, IOException{
	    
	  
	     Thread.sleep(5000);
	     driver.findElement(By.linkText("Concept Notes")).click();
		// driver.findElement(By.name("Concept Notes")).click();
		 Thread.sleep(5000);
	     Workbook wb = Workbook.getWorkbook(srcCNRegression);
	     Thread.sleep(5000);
	     Record = wb.getSheet("Record").getCell(0,1).getContents();
	     Thread.sleep(5000);
	     driver.findElement(By.linkText(Record)).click();
		 Thread.sleep(5000); 
		 driver.findElement(By.linkText("PRINCIPAL RECIPIENTS")).click();
		 Thread.sleep(8000);
		 driver.findElement(By.id("page:frm:rpt:0:cmdLinkDeleteGoal")).click();
		 Thread.sleep(5000);
		 driver.findElement(By.name("page:frm:rpt:0:j_id78")).click(); 
		 Thread.sleep(5000);
		 System.out.println("PR is deleted");
		 
 	  
     } 
  @Test (priority= 12)
	
 	public void GoalCreation() throws InterruptedException, BiffException, IOException{
 		
 		 Workbook wb = Workbook.getWorkbook(srcCNRegression);
 	     Goaltitle = wb.getSheet("GoalDetails").getCell(0, 1).getContents();
 	     rowcount = wb.getSheet("GoalDetails").getRows();
 	     Record = wb.getSheet("Record").getCell(0,1).getContents();
		 Thread.sleep(5000); 
		 driver.findElement(By.linkText("Concept Notes")).click();
		 RecordLink = driver.findElement(By.linkText(Record));
		 //HighlightElement.elementHighlight(RecordLink);
		 RecordLink.click();
 		 Thread.sleep(5000);
 		 for (i=1; i<rowcount; ++i) {
 			 driver.findElement(By.linkText("GOALS & IMPACT INDICATORS")).click();
 			 Thread.sleep(5000);
 			 driver.findElement(By.linkText("Add Goal")).click();  
 			 Thread.sleep(5000);
 			 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
 		     WebElement goalname = driver.findElement(By.id("pg:frm:itaGDec"));
 		     HighlightElement.elementHighlight(goalname);
 		     goalname.sendKeys(Goaltitle);
 		     Thread.sleep(5000);
 		     driver.findElement(By.linkText("Save")).click(); 
 		     Thread.sleep(5000);
 		 }
 		   }
  
  @Test(priority=13)
	
	public void StdImpactIndicator() throws BiffException, IOException, InterruptedException {
		
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
	    ImpactIndicatorName = wb.getSheet("ImpactIndicatorList").getCell(1,1).getContents();
		Integer x = Integer.valueOf(ImpactIndicatorName);
		String BaseLineval = wb.getSheet("ImpactIndicatorList").getCell(2, 1).getContents();
		String YearVal = wb.getSheet("ImpactIndicatorList").getCell(3, 1).getContents();
		String Sourceval = wb.getSheet("ImpactIndicatorList").getCell(4, 1).getContents();
		String DecimalPlaces = wb.getSheet("ImpactIndicatorList").getCell(5, 1).getContents();	
		String Impval2014 = wb.getSheet("ImpactIndicatorList").getCell(1,4).getContents();
		String Impval2015 = wb.getSheet("ImpactIndicatorList").getCell(1,5).getContents();
		String Impval2016 = wb.getSheet("ImpactIndicatorList").getCell(1,6).getContents();
		String Comment = wb.getSheet("ImpactIndicatorList").getCell(1, 7).getContents();
		String DueDate1 = wb.getSheet("ImpactIndicatorList").getCell(2,4).getContents();
		String DueDate2 = wb.getSheet("ImpactIndicatorList").getCell(2, 5).getContents();
		String DueDate3 = wb.getSheet("ImpactIndicatorList").getCell(2, 6).getContents();
		Thread.sleep(2000);
		Record = wb.getSheet("Record").getCell(0,1).getContents();
		Thread.sleep(5000); 
		driver.findElement(By.linkText("Concept Notes")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(Record)).click();
		//HighlightElement.elementHighlight(RecordLink);
		//RecordLink.click();
		WebElement GoalImpLink  = driver.findElement(By.linkText("GOALS & IMPACT INDICATORS"));
		HighlightElement.elementHighlight(GoalImpLink);
		GoalImpLink.click();
		Thread.sleep(5000);
		//Click on Add Standard Indicator button
		WebElement StdAddInd = driver.findElement(By.linkText("Add Standard Indicator"));
		HighlightElement.elementHighlight(StdAddInd);
		StdAddInd.click();
		Thread.sleep(5000);
		// Select Standard Indicator value
		WebElement selectIndicator=driver.findElement(By.id("pg:frm:SelectCatIndicator"));
		HighlightElement.elementHighlight(selectIndicator);
		Select Indicator=new Select(selectIndicator);
		Thread.sleep(5000);
		Indicator.selectByIndex(x);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@id='pg:frm:SelectIndicatorPicklist']/strong")).click();
		Thread.sleep(20000);
		// select Goal value
		driver.findElement(By.id("pg:frm:StdPanelGoal")).click();
		Thread.sleep(15000);
		WebElement DecimalPlace= driver.findElement(By.id("pg:frm:ipdecimalplacestand"));
		DecimalPlace.click();
		HighlightElement.elementHighlight(DecimalPlace);
		Select DecimalPick = new Select(DecimalPlace);
		DecimalPick.selectByValue(DecimalPlaces);
		// Select Baseline Value
		WebElement BaseLine = driver.findElement(By.id("pg:frm:ipStdBy1"));
		BaseLine.clear();
		HighlightElement.elementHighlight(BaseLine);
		BaseLine.sendKeys(BaseLineval);
		Thread.sleep(5000);
		// Select Baseline Year
		WebElement Year = driver.findElement(By.id("pg:frm:j_id328"));
		Year.click();
		Select YearPick = new Select(Year);
		Thread.sleep(5000);
		YearPick.selectByValue(YearVal);
		Thread.sleep(5000);
		// Select Baseline Source
		WebElement Source = driver.findElement(By.id("pg:frm:j_id331"));
		Source.click();
		Select SourcePick = new Select(Source);
		SourcePick.selectByValue(Sourceval);
		//HighlightElement.elementHighlight(Sourceval);
		//new Select(driver.findElement(By.id("pg:frm:j_id331"))).selectByVisibleText("Administrative records");
		driver.findElement(By.id("pg:frm:j_id337")).click();
		Thread.sleep(5000);
		// Enter value for Year 2014
		WebElement val2014 = driver.findElement(By.id("pg:frm:ipStdTy1"));
		val2014.clear();
		HighlightElement.elementHighlight(val2014);
		val2014.sendKeys(Impval2014);
		//// Enter value for Year 2015
		WebElement val2015 = driver.findElement(By.id("pg:frm:ipStdTy2"));
		val2015.clear();
		HighlightElement.elementHighlight(val2015);
		val2015.sendKeys(Impval2015);
		//driver.findElement(By.id("pg:frm:j_id339")).click();
		// Enter value for Year 2016
		WebElement val2016 = driver.findElement(By.id("pg:frm:ipStdTy3"));
		val2016.clear();
		HighlightElement.elementHighlight(val2016);
		val2016.sendKeys(Impval2016);
		// Enter Due Date Value
		WebElement DDate2 = driver.findElement(By.id("pg:frm:j_id339"));
		DDate2.clear();
		DDate2.sendKeys(DueDate2);
		driver.findElement(By.xpath("//td[@onclick='DatePicker.datePicker.selectDate(this);']")).click();
		// Enter Due date Value
		WebElement DDate1 = driver.findElement(By.id("pg:frm:j_id337"));
		DDate1.clear();
		HighlightElement.elementHighlight(DDate1);
		DDate1.sendKeys(DueDate1);
		// Enter Due Date value
		WebElement DDate3 = driver.findElement(By.id("pg:frm:j_id341"));
		DDate3.click();
		DDate3.clear();
		HighlightElement.elementHighlight(DDate3);
		DDate3.sendKeys(DueDate3);
		// Enter Comment in the comment section for indicator
		WebElement CommentSection = driver.findElement(By.name("pg:frm:j_id345"));
		HighlightElement.elementHighlight(CommentSection);
		CommentSection.sendKeys(Comment);
		//Click on save button
		WebElement Save = driver.findElement(By.xpath("//a[@id='pg:frm:j_id312']/strong"));
		HighlightElement.elementHighlight(Save);
		Save.click();
			
			}
	
   //Add a custom Impact indicator to the CN with status as not yet submitted
 @Test  (priority = 14)
 public static void CustomImpactInd() throws BiffException, IOException, Exception  {
			
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		Record = wb.getSheet("Record").getCell(0,1).getContents();
		String data01= wb.getSheet("Impact_Ind_Details_Custom").getCell(0,1).getContents();
		String data02= wb.getSheet("Impact_Ind_Details_Custom").getCell(1,1).getContents();
		String data03= wb.getSheet("Impact_Ind_Details_Custom").getCell(2,1).getContents();
		String data04= wb.getSheet("Impact_Ind_Details_Custom").getCell(3,1).getContents();
		String data05= wb.getSheet("Impact_Ind_Details_Custom").getCell(4,1).getContents();
		String data06= wb.getSheet("Impact_Ind_Details_Custom").getCell(5,1).getContents();
		String data07= wb.getSheet("Impact_Ind_Details_Custom").getCell(6,1).getContents();
		String data08= wb.getSheet("Impact_Ind_Details_Custom").getCell(7,1).getContents();
		String data09= wb.getSheet("Impact_Ind_Details_Custom").getCell(8,1).getContents();
		String data10= wb.getSheet("Impact_Ind_Details_Custom").getCell(9,1).getContents();
		String data11= wb.getSheet("Impact_Ind_Details_Custom").getCell(10,1).getContents();
		String data12= wb.getSheet("Impact_Ind_Details_Custom").getCell(11,1).getContents();
		String data13= wb.getSheet("Impact_Ind_Details_Custom").getCell(12,1).getContents();
		String data14= wb.getSheet("Impact_Ind_Details_Custom").getCell(13,1).getContents();
	    System.out.println(Record);
		Thread.sleep(5000);
		WebElement record=driver.findElement(By.linkText(Record));
		HighlightElement.elementHighlight(record);
		record.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Click on GOALS & IMPACT INDICATORS link
		WebElement impact = driver.findElement(By.linkText("GOALS & IMPACT INDICATORS"));
		Thread.sleep(1000);
		HighlightElement.elementHighlight(impact);
		impact.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Click on Add Custom Indicator link
		WebElement ind = driver.findElement(By.linkText("Add Custom Indicator"));
		HighlightElement.elementHighlight(ind);
		Thread.sleep(2000);
		ind.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter indicator name
		WebElement indname = driver.findElement(By.id("pg:frm:Fullname"));
		//HighlightElement.elementHighlight(indname);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		indname.sendKeys(data01);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Select the datatype
		WebElement datatype = driver.findElement(By.id("pg:frm:datatype"));
		//HighlightElement.elementHighlight(datatype);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		datatype.sendKeys(data02);
		//Select goal to link
		driver.findElement(By.xpath("//div[2]/div/div/table/tbody/tr/td/span/span")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//WebElement country = driver.findElement(By.id("pg:frm:CustomSelectCountry"));
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//country.sendKeys(data03);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Select decimal places field
		WebElement decimal = driver.findElement(By.id("pg:frm:ipdecimalplacenew"));
		//HighlightElement.elementHighlight(decimal);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		decimal.sendKeys(data04);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter the baseline value
		WebElement value = driver.findElement(By.id("pg:frm:BaselineValue"));
		//HighlightElement.elementHighlight(value);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		value.sendKeys(data05);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter baseline year
		WebElement year = driver.findElement(By.id("pg:frm:BaselineYear"));
		//HighlightElement.elementHighlight(year);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		year.sendKeys(data06);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Select baseline source value from dropdown
		WebElement source = driver.findElement(By.id("pg:frm:BaselineSource"));
		//HighlightElement.elementHighlight(source);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(5000);
		source.sendKeys(data07);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target year value
		WebElement year1 = driver.findElement(By.id("pg:frm:Tyear1"));
		//HighlightElement.elementHighlight(year1);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		year1.sendKeys(data08);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target year value
		WebElement year2 = driver.findElement(By.id("pg:frm:Tyear2"));
		//HighlightElement.elementHighlight(year2);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		year2.sendKeys(data09);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target year value
		WebElement year3 = driver.findElement(By.id("pg:frm:Tyear3"));
		//HighlightElement.elementHighlight(year3);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		year3.sendKeys(data10);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter report due date
		WebElement report1 = driver.findElement(By.id("pg:frm:Ryear1"));
		HighlightElement.elementHighlight(report1);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		report1.sendKeys(data11);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter report due date
		WebElement report2 = driver.findElement(By.id("pg:frm:Ryear2"));
		//HighlightElement.elementHighlight(report2);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		report2.sendKeys(data12);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter report due date
		WebElement report3 = driver.findElement(By.id("pg:frm:Ryear3"));
		//HighlightElement.elementHighlight(report3);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		report3.sendKeys(data13);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter the comment
		WebElement comment = driver.findElement(By.id("pg:frm:comment"));
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//HighlightElement.elementHighlight(comment);
		comment.sendKeys(data14);
		//Click on save button
		WebElement save=driver.findElement(By.xpath("//span[@id='pg:frm:j_id253']/a/strong"));
		HighlightElement.elementHighlight(save);
		Thread.sleep(5000);
		save.click();
	}

  @Test (priority = 15)
	
	public void ObjectiveCreation() throws InterruptedException, BiffException, IOException{
		
		 Workbook wb = Workbook.getWorkbook(srcCNRegression);
		 Objectivetitle = wb.getSheet("ObjectiveDetails").getCell(0, 1).getContents();
		 rowcount = wb.getSheet("ObjectiveDetails").getRows();
		 Thread.sleep(5000);
		 Record = wb.getSheet("Record").getCell(0,1).getContents();
		 driver.findElement(By.linkText("Concept Notes")).click();
		 RecordLink = driver.findElement(By.linkText(Record));
		 Thread.sleep(2000);
		 //HighlightElement.elementHighlight(RecordLink);
		 Thread.sleep(6000);
		 RecordLink.click();
		 driver.findElement(By.linkText("OBJECTIVES & OUTCOME INDICATORS")).click();
		 for (i=1; i<rowcount; ++i) {
			 Thread.sleep(5000);
			 driver.findElement(By.linkText("Add Objective")).click();  
			 Thread.sleep(5000);
			 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			 Thread.sleep(5000);
			 WebElement objectivename = driver.findElement(By.id("page:frm:FullNameNew"));
		     objectivename.sendKeys(Objectivetitle);
		     driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		     driver.findElement(By.linkText("Save")).click(); 
		     Thread.sleep(5000);
		   }
		 }			

  @Test  (priority = 16)
	
	public void StdOutcomeIndicator() throws InterruptedException, BiffException, IOException {
		
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
	    OutcomeIndicatorName = wb.getSheet("OutcomeIndicator").getCell(1,1).getContents();
		System.out.println(OutcomeIndicatorName);
		Integer x = Integer.valueOf(OutcomeIndicatorName);
		System.out.println(x);
		String BaseLineval = wb.getSheet("OutcomeIndicator").getCell(2, 1).getContents();
		String YearVal = wb.getSheet("OutcomeIndicator").getCell(3, 1).getContents();
		String Sourceval = wb.getSheet("OutcomeIndicator").getCell(4, 1).getContents();
		String DecimalPlaces = wb.getSheet("OutcomeIndicator").getCell(5, 1).getContents();	
		String Impval2014 = wb.getSheet("OutcomeIndicator").getCell(1,4).getContents();
		String Impval2015 = wb.getSheet("OutcomeIndicator").getCell(1,5).getContents();
		String Impval2016 = wb.getSheet("OutcomeIndicator").getCell(1,6).getContents();
		String Comment = wb.getSheet("OutcomeIndicator").getCell(1, 7).getContents();
		String DueDate1 = wb.getSheet("OutcomeIndicator").getCell(2,4).getContents();
		String DueDate2 = wb.getSheet("OutcomeIndicator").getCell(2, 5).getContents();
		String DueDate3 = wb.getSheet("OutcomeIndicator").getCell(2, 6).getContents();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Concept Notes")).click();
		Thread.sleep(2000);
		RecordLink = driver.findElement(By.linkText(Record));
		HighlightElement.elementHighlight(RecordLink);
		RecordLink.click();
		Thread.sleep(5000);
		WebElement GoalImpLink  = driver.findElement(By.linkText("OBJECTIVES & OUTCOME INDICATORS"));
		HighlightElement.elementHighlight(GoalImpLink);
		GoalImpLink.click();
		Thread.sleep(5000);
		// Click on Add Indicator button
		WebElement StdAddInd = driver.findElement(By.linkText("Add Standard Indicator"));
		HighlightElement.elementHighlight(StdAddInd);
		StdAddInd.click();
		Thread.sleep(5000);
		// Select Indicator
		WebElement selectIndicator=driver.findElement(By.id("page:frm:SelectCatIndicator"));
		HighlightElement.elementHighlight(selectIndicator);
		Select Indicator=new Select(selectIndicator);
		Thread.sleep(5000);
		Indicator.selectByIndex(x);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@id='page:frm:SelectIndicatorPicklist']/strong")).click();
		Thread.sleep(20000);
		driver.findElement(By.id("page:frm:StdPanelGoal")).click();
		Thread.sleep(15000);
		//Select Decimal place value
		WebElement DecimalPlace= driver.findElement(By.id("page:frm:ipdecimalplacestand"));
		DecimalPlace.click();
		Select DecimalPick = new Select(DecimalPlace);
		DecimalPick.selectByValue(DecimalPlaces);
		//Select Baseline Value
		WebElement BaseLine = driver.findElement(By.id("page:frm:stdBvalue"));
		BaseLine.clear();
		BaseLine.sendKeys(BaseLineval);
		Thread.sleep(5000);
		//Select Baseline  year
		WebElement Year = driver.findElement(By.id("page:frm:j_id340"));
		Year.click();
		Select YearPick = new Select(Year);
		Thread.sleep(5000);
		YearPick.selectByValue(YearVal);
		Thread.sleep(5000);
		//Select source value
		WebElement Source = driver.findElement(By.id("page:frm:j_id343"));
		Source.click();
		Select SourcePick = new Select(Source);
		SourcePick.selectByValue(Sourceval);
		Thread.sleep(5000);
		//Enter Value in Year 2014
		WebElement val2014 = driver.findElement(By.id("page:frm:stdTargetY1"));
		val2014.clear();
		val2014.sendKeys(Impval2014);
		//Enter Value in Year 2015
		WebElement val2015 = driver.findElement(By.id("page:frm:stdTargetY2"));
		val2015.clear();
		val2015.sendKeys(Impval2015);
		//driver.findElement(By.id("pg:frm:j_id339")).click();
		// Enter Value in Year 2016
		WebElement val2016 = driver.findElement(By.id("page:frm:stdTargetY3"));
		val2016.clear();
		val2016.sendKeys(Impval2016);
		// Select Due Date
		WebElement DDate2 = driver.findElement(By.id("page:frm:j_id349"));
		DDate2.clear();
		HighlightElement.elementHighlight(DDate2);
		DDate2.sendKeys(DueDate2);
		driver.findElement(By.xpath("//td[@onclick='DatePicker.datePicker.selectDate(this);']")).click();
		WebElement DDate1 = driver.findElement(By.id("page:frm:j_id351"));
		DDate1.clear();
		HighlightElement.elementHighlight(DDate1);
		DDate1.sendKeys(DueDate1);
		// Select Due date
		WebElement DDate3 = driver.findElement(By.id("page:frm:j_id353"));
		DDate3.click();
		HighlightElement.elementHighlight(DDate3);
		DDate3.clear();
		Thread.sleep(1000);
		DDate3.sendKeys(DueDate3);
		//Enter Comment in the comment section of indicator
		WebElement commentSection =  driver.findElement(By.name("page:frm:j_id357"));
		HighlightElement.elementHighlight(commentSection);
		commentSection.sendKeys(Comment);
		//Click on save button
		driver.findElement(By.xpath("//a[@id='page:frm:j_id315']/strong")).click();
			
			}
  
//Add a custom Outcome indicator to the CN with status as not yet submitted
 @Test  (priority = 17)
 public static void CustomOutcomeInd() throws BiffException, IOException, Exception  {
			
	Workbook wb = Workbook.getWorkbook(srcCNRegression);
	
	Record = wb.getSheet("Record").getCell(0,1).getContents();
	String data01=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(0,1).getContents();
	String data02=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(1,1).getContents();
	String data03=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(2,1).getContents();
	String data04=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(3,1).getContents();
	String data05=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(4,1).getContents();
	String data06=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(5,1).getContents();
	String data07=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(6,1).getContents();
	String data08=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(7,1).getContents();
	String data09=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(8,1).getContents();
	String data10=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(9,1).getContents();
	String data11=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(10,1).getContents();
	String data12=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(11,1).getContents();
	String data13=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(12,1).getContents();
	String data14=  wb.getSheet("Outcome_Ind_Details_Custom").getCell(13,1).getContents();
	
	Thread.sleep(5000);
	//Click on "OBJECTIVES & OUTCOME INDICATORS link
	WebElement outcome = driver.findElement(By.linkText("OBJECTIVES & OUTCOME INDICATORS"));
	HighlightElement.elementHighlight(outcome);
	Thread.sleep(5000);
	outcome.click();
	//driver.findElement(By.xpath("//td[2]/div/a/strong")).click();
	//Click on add custom indicator link
	Thread.sleep(1000);
	WebElement ind =driver.findElement(By.linkText("Add Custom Indicator"));
	HighlightElement.elementHighlight(ind);
	Thread.sleep(2000);
	ind.click();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(5000);
	//Enter indicator name
	WebElement indname = driver.findElement(By.id("page:frm:Fullname"));
	//HighlightElement.elementHighlight(indname);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	indname.sendKeys(data01);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Select datatype value
	WebElement datatype = driver.findElement(By.id("page:frm:datatype"));
	//HighlightElement.elementHighlight(datatype);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	datatype.sendKeys(data02);
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[2]/div/div/table/tbody/tr/td/span/span")).click();
	Thread.sleep(2000);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//WebElement country = driver.findElement(By.id("page:frm:CustomSelectCountry"));
	//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//country.sendKeys(data03);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Select decimal value
	WebElement decimal = driver.findElement(By.id("page:frm:ipdecimalplacenew"));
	//HighlightElement.elementHighlight(decimal);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	decimal.sendKeys(data04);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter baseline value
	WebElement value = driver.findElement(By.id("page:frm:BaselineValue"));
	//HighlightElement.elementHighlight(value);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	value.sendKeys(data05);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter baseline year value
	WebElement year = driver.findElement(By.id("page:frm:BaselineYear"));
	//HighlightElement.elementHighlight(year);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	year.sendKeys(data06);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter baseline source value
	WebElement source = driver.findElement(By.id("page:frm:BaselineSource"));
	//HighlightElement.elementHighlight(source);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(2000);
	source.sendKeys(data07);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter target year
	WebElement year1 = driver.findElement(By.id("page:frm:Tyear1"));
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//HighlightElement.elementHighlight(year1);
	Thread.sleep(2000);
	year1.sendKeys(data08);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter target year
	WebElement year2 = driver.findElement(By.id("page:frm:Tyear2"));
	//HighlightElement.elementHighlight(year2);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(2000);
	year2.sendKeys(data09);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter target year
	WebElement year3 = driver.findElement(By.id("page:frm:Tyear3"));
	//HighlightElement.elementHighlight(year3);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(2000);
	year3.sendKeys(data10);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter report due date
	WebElement report1 = driver.findElement(By.id("page:frm:Ryear1"));
	//HighlightElement.elementHighlight(report1);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(2000);
	report1.sendKeys(data11);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter report due date
	WebElement report2 = driver.findElement(By.id("page:frm:Ryear2"));
	//HighlightElement.elementHighlight(report2);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(2000);
	report2.sendKeys(data12);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter report due date
	WebElement report3 = driver.findElement(By.id("page:frm:Ryear3"));
	//HighlightElement.elementHighlight(report3);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(2000);
	report3.sendKeys(data13);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter comment
	WebElement comment = driver.findElement(By.id("page:frm:comment"));
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//HighlightElement.elementHighlight(comment);
	Thread.sleep(2000);
	comment.sendKeys(data14);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(2000);
	//Click on save button
	WebElement save=driver.findElement(By.xpath("//span[@id='page:frm:j_id265']/a/strong"));
		HighlightElement.elementHighlight(save);
		Thread.sleep(5000);
		save.click();
		
		}
 

	//Add a custom Output indicator to the CN with status as not yet submitted
@Test  (priority = 18)
public static void CustomeCoverageOutput() throws BiffException, IOException, Exception  {
				
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		String data00 = wb.getSheet("Output_Ind_Details").getCell(0,1).getContents();
		String data01=  wb.getSheet("Output_Ind_Details").getCell(1,1).getContents();
		String data02=  wb.getSheet("Output_Ind_Details").getCell(2,1).getContents();
		String data03=  wb.getSheet("Output_Ind_Details").getCell(3,1).getContents();
		String data04=  wb.getSheet("Output_Ind_Details").getCell(4,1).getContents();
		String data05=  wb.getSheet("Output_Ind_Details").getCell(5,1).getContents();
		String data06=  wb.getSheet("Output_Ind_Details").getCell(6,1).getContents();
		String data07=  wb.getSheet("Output_Ind_Details").getCell(7,1).getContents();
		String data08=  wb.getSheet("Output_Ind_Details").getCell(8,1).getContents();
		String data09=  wb.getSheet("Output_Ind_Details").getCell(9,1).getContents();
		String data10=  wb.getSheet("Output_Ind_Details").getCell(10,1).getContents();
		String data11=  wb.getSheet("Output_Ind_Details").getCell(11,1).getContents();
		String data12=  wb.getSheet("Output_Ind_Details").getCell(12,1).getContents();
		String data13=  wb.getSheet("Output_Ind_Details").getCell(13,1).getContents();
		String data14=  wb.getSheet("Output_Ind_Details").getCell(14,1).getContents();
		String data15=  wb.getSheet("Output_Ind_Details").getCell(15,1).getContents();
		String data16=  wb.getSheet("Output_Ind_Details").getCell(16,1).getContents();
		String data17=  wb.getSheet("Output_Ind_Details").getCell(17,1).getContents();
		String data18=  wb.getSheet("Output_Ind_Details").getCell(18,1).getContents();
		String data19=  wb.getSheet("Output_Ind_Details").getCell(19,1).getContents();

		
		Thread.sleep(5000);
		//Click on arrow button of Coverage and Output Indicators link
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//img[@title='Expand'])[2]")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(8000);
		//driver.findElement(By.xpath("//li[@id='Module7']/div/a")).click();
		WebElement intervention = driver.findElement(By.linkText(data00));
		HighlightElement.elementHighlight(intervention);
		Thread.sleep(2000);
		intervention.click();
		Thread.sleep(2000);
		//Click on Add Custom indicator link
		WebElement ind =driver.findElement(By.linkText("Add Custom Indicator"));
		HighlightElement.elementHighlight(ind);
		Thread.sleep(2000);
		ind.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//Enter name of the indicator
		WebElement indname = driver.findElement(By.name("page:frm:j_id414"));
		//HighlightElement.elementHighlight(indname);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		indname.sendKeys(data01);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Select datatype from drop down
		WebElement datatype = driver.findElement(By.id("page:frm:DataType"));
		//HighlightElement.elementHighlight(datatype);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		datatype.sendKeys(data02);
		//Select PR name
		WebElement PR = driver.findElement(By.id("page:frm:newCustPR"));
		//HighlightElement.elementHighlight(PR);
		PR.sendKeys(data03);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter geographical location
		WebElement Geographical = driver.findElement(By.id("page:frm:TargetArea1"));
		//HighlightElement.elementHighlight(Geographical);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Geographical.sendKeys(data04);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter decimal values
		WebElement decimal = driver.findElement(By.id("page:frm:ipdecimalplacenew"));
		//HighlightElement.elementHighlight(decimal);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    decimal.sendKeys(data05);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target accumulation value
		WebElement target = driver.findElement(By.id("page:frm:j_id443"));
		//HighlightElement.elementHighlight(target);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		target.sendKeys(data06);
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//WebElement country = driver.findElement(By.id("page:frm:custSelectCountry"));
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//country.sendKeys(data07);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter baseline value
		WebElement baseline = driver.findElement(By.name("page:frm:ipbvaluenew"));
		//HighlightElement.elementHighlight(baseline);
		Thread.sleep(5000);
		baseline.sendKeys(data08);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter baseline year value
		WebElement year = driver.findElement(By.name("page:frm:j_id455"));
		//HighlightElement.elementHighlight(year);
		Thread.sleep(5000);
		year.sendKeys(data09);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter baseline source value
		WebElement source = driver.findElement(By.name("page:frm:j_id458"));
		//HighlightElement.elementHighlight(source);
		Thread.sleep(5000);
		source.sendKeys(data10);
		//Enter target allocation for year1
		WebElement target1 = driver.findElement(By.id("page:frm:ipIndIY1new"));
		//HighlightElement.elementHighlight(target1);
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		target1.sendKeys(data11);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target allocation for year2
		WebElement target2 = driver.findElement(By.id("page:frm:ipIndIY2new"));
		//HighlightElement.elementHighlight(target2);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(5000);
		target2.sendKeys(data12);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target allocation for year3
		WebElement target3 = driver.findElement(By.id("page:frm:ipIndIY3new"));
		//HighlightElement.elementHighlight(target3);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(5000);
		target3.sendKeys(data13);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target allocation for year4
		WebElement target4 = driver.findElement(By.id("page:frm:ipIndIY4new"));
		//HighlightElement.elementHighlight(target4);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		target4.sendKeys(data14);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target allocation for year1
		WebElement target5 = driver.findElement(By.id("page:frm:ipIndAY1new"));
		//HighlightElement.elementHighlight(target5);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		target5.sendKeys(data15);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target allocation for year2
		WebElement target6 = driver.findElement(By.id("page:frm:ipIndAY2new"));
		//HighlightElement.elementHighlight(target6);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		target6.sendKeys(data16);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target allocation for year3
		WebElement target7 = driver.findElement(By.id("page:frm:ipIndAY3new"));
		//HighlightElement.elementHighlight(target7);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		target7.sendKeys(data17);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Enter target allocation for year4
		WebElement target8 = driver.findElement(By.id("page:frm:ipIndAY4new"));
		//HighlightElement.elementHighlight(target8);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000);
		target8.sendKeys(data18);
		//Enter comment
		WebElement comment = driver.findElement(By.name("page:frm:j_id525"));
		//HighlightElement.elementHighlight(comment);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		comment.sendKeys(data19);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Click on save button
		WebElement save =driver.findElement(By.xpath("//a[@id='page:frm:j_id425']/strong"));
		HighlightElement.elementHighlight(save);
		Thread.sleep(2000);
		save.click();
	}

//Add Module Budget to the CN with status as not yet submitted
 @Test  (priority = 19)
 public static void CNModuleBudget() throws BiffException, IOException, Exception  {
			
	Workbook wb = Workbook.getWorkbook(srcCNRegression);
			
	String data00 = wb.getSheet("Module_Bdgt_Details").getCell(0,1).getContents();
	String data01=  wb.getSheet("Module_Bdgt_Details").getCell(1,1).getContents();
	String data02=  wb.getSheet("Module_Bdgt_Details").getCell(2,1).getContents();
	String data03=  wb.getSheet("Module_Bdgt_Details").getCell(3,1).getContents();
	String data04=  wb.getSheet("Module_Bdgt_Details").getCell(4,1).getContents();
	String data05=  wb.getSheet("Module_Bdgt_Details").getCell(5,1).getContents();
	String data06=  wb.getSheet("Module_Bdgt_Details").getCell(6,1).getContents();
	String data07=  wb.getSheet("Module_Bdgt_Details").getCell(7,1).getContents();
	String data08=  wb.getSheet("Module_Bdgt_Details").getCell(8,1).getContents();
	String data09=  wb.getSheet("Module_Bdgt_Details").getCell(9,1).getContents();
							

	Thread.sleep(5000);
	driver.findElement(By.xpath("(//img[@title='Expand'])[3]")).click();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(5000);
	//driver.findElement(By.xpath("//li[36]/div/a")).click();
	//Click on module name
	WebElement module =driver.findElement(By.linkText(data00));	
	HighlightElement.elementHighlight(module);
	Thread.sleep(5000);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	module.click();
	Thread.sleep(2000);
	//Click on Enter description button
	WebElement enter =driver.findElement(By.id("page:frm:rptrCNI:0:btnEnterDesc"));
	Thread.sleep(2000);
	HighlightElement.elementHighlight(enter);
	Thread.sleep(5000);
	enter.click();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(5000);
	//Enter description
	WebElement desc = driver.findElement(By.id("page:frm:rptrCNI:0:txtDescIntervention"));
	//HighlightElement.elementHighlight(desc);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	desc.sendKeys(data01);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Click on ok button
	WebElement ok1 =driver.findElement(By.name("page:frm:rptrCNI:0:j_id69"));
	HighlightElement.elementHighlight(ok1);
	Thread.sleep(5000);
	ok1.click();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter target allocation
	WebElement target1 = driver.findElement(By.id("page:frm:rptrCNI:0:rptrGI:0:txtIndicativeY1"));
	//HighlightElement.elementHighlight(target1);
	target1.sendKeys(data02);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter target allocation
	WebElement target2 = driver.findElement(By.id("page:frm:rptrCNI:0:rptrGI:0:txtIndicativeY2"));
	//HighlightElement.elementHighlight(target2);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	target2.sendKeys(data03);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter target allocation
	WebElement target3 = driver.findElement(By.id("page:frm:rptrCNI:0:rptrGI:0:txtIndicativeY3"));
	//HighlightElement.elementHighlight(target3);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	target3.sendKeys(data04);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	WebElement target4 = driver.findElement(By.id("page:frm:rptrCNI:0:rptrGI:0:txtAboveY1"));
	//HighlightElement.elementHighlight(target4);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	target4.sendKeys(data05);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter target allocation
	WebElement target5 = driver.findElement(By.id("page:frm:rptrCNI:0:rptrGI:0:txtAboveY2"));
	//HighlightElement.elementHighlight(target5);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	target5.sendKeys(data06);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter target allocation
	WebElement target6 = driver.findElement(By.id("page:frm:rptrCNI:0:rptrGI:0:txtAboveY3"));
	//HighlightElement.elementHighlight(target6);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	target6.sendKeys(data07);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//td[7]/a/strong")).click();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter assumption value
	WebElement assumption1 = driver.findElement(By.id("page:frm:rptrCNI:0:rptrGI:0:txtCostAss"));
	//HighlightElement.elementHighlight(assumption1);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	assumption1.sendKeys(data08);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//Enter assumption value
	WebElement assumption2 = driver.findElement(By.id("page:frm:rptrCNI:0:rptrGI:0:txtCNFund"));
	//HighlightElement.elementHighlight(assumption2);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	assumption2.sendKeys(data09);
	//Click on ok button
	WebElement ok2 =driver.findElement(By.name("page:frm:rptrCNI:0:rptrGI:0:j_id137"));
	HighlightElement.elementHighlight(ok2);
	Thread.sleep(2000);
	ok2.click();
	//Click on save button
	WebElement save =driver.findElement(By.id("page:frm:btnSaveDraft"));
	HighlightElement.elementHighlight(save);
	Thread.sleep(2000);
	save.click();

	}

 @Test (priority = 20)
	
	public void ChangeSubmissionDate() throws IOException, InterruptedException, BiffException {
		
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		String DateVal = wb.getSheet("CNSubmissionForm").getCell(0, 1).getContents();
		String LanguageVal = wb.getSheet("CNSubmissionForm").getCell(1, 1).getContents();
		String JustificationVal =  wb.getSheet("CNSubmissionForm").getCell(2, 1).getContents();
		Thread.sleep(2000);
		Record = wb.getSheet("Record").getCell(0,1).getContents();
		Thread.sleep(5000); 
		driver.findElement(By.linkText(Record)).click();
		driver.findElement(By.linkText("Change Submission Date")).click();
		Thread.sleep(2000);
		WebElement SubmissionDate = driver.findElement(By.id("CNoverview:frm:subDateList"));
		SubmissionDate.click();
		Select SubDatePick = new Select(SubmissionDate);
		Thread.sleep(2000);
		SubDatePick.selectByValue(DateVal);
		Thread.sleep(2000);
		WebElement SelLanguage = driver.findElement(By.id("CNoverview:frm:subLang"));
		SelLanguage.click();
		Select LanguagePick = new Select(SelLanguage);
		LanguagePick.selectByValue(LanguageVal);
		Thread.sleep(2000);
		WebElement Justification = driver.findElement(By.id("CNoverview:frm:justification"));
		Justification.click();
		Justification.sendKeys(JustificationVal);
		Thread.sleep(2000);
		driver.findElement(By.id("CNoverview:frm:j_id118")).click();
	}

	
	@Test  (priority = 21)
	
	public void UploadFile() throws BiffException, IOException, InterruptedException {
		
		int j;
		int k;
		int l;
		int m;
		Thread.sleep(2000);
		Workbook wb = Workbook.getWorkbook(srcCNRegression);
		int rowcount = wb.getSheet("ManageDocument").getRows();
		Record = wb.getSheet("Record").getCell(0,1).getContents();
		Thread.sleep(5000); 
		RecordLink = driver.findElement(By.linkText(Record));
		HighlightElement.elementHighlight(RecordLink);
		Thread.sleep(5000); 
		RecordLink.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("MANAGE DOCUMENTS")).click();
		Thread.sleep(2000);
		for (i=1; i<rowcount; ++i) {
				j = k = l = m = i;
				String FileType = wb.getSheet("ManageDocument").getCell(0, i).getContents();
				String Language = wb.getSheet("ManageDocument").getCell(1, j).getContents();
				String FilePath = wb.getSheet("ManageDocument").getCell(3, k).getContents();
				String Section = wb.getSheet("ManageDocument").getCell(4, l).getContents();
				String Description =  wb.getSheet("ManageDocument").getCell(5, m).getContents();
				
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[2]/a/strong")).click();
                Thread.sleep(2000);
				WebElement SelectType = driver.findElement(By.id("page:frm:ifType"));
				SelectType.click();
				Select TypePick = new Select(SelectType);
				TypePick.selectByValue(FileType);
				
			    //new Select(driver.findElement(By.id("page:frm:ifType"))).selectByVisibleText(FileType);
			    Thread.sleep(2000);
			    new Select(driver.findElement(By.id("page:frm:ifLanguage"))).selectByVisibleText(Language);
			    driver.findElement(By.id("page:frm:iSection")).clear();
			    Thread.sleep(2000);
			    driver.findElement(By.id("page:frm:iSection")).sendKeys(Section);
			    Thread.sleep(2000);
			    driver.findElement(By.id("page:frm:ipFile"));
			    Thread.sleep(2000);
			    //driver.findElement(By.id("page:frm:ipFile")).clear();
			    driver.findElement(By.id("page:frm:ipFile")).sendKeys(FilePath);
			    Thread.sleep(2000);
			    driver.findElement(By.id("page:frm:ifDescription")).click();
			    driver.findElement(By.id("page:frm:ifDescription")).click();
			    driver.findElement(By.id("page:frm:ifDescription")).clear();
			    driver.findElement(By.id("page:frm:ifDescription")).sendKeys(Description);
			    driver.findElement(By.cssSelector("strong")).click();
			    //driver.findElement(By.name("page:frm:j_id20")).click();
			  }
	}
	
 
//Verify CCM user is able to submit CN by clicking on Submit Concept Note button

	@Test  (priority = 22)
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

	}

//Verify CCM user is not able to edit details after concept note is submitted.
	
	@Test  (priority = 23)
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

	@Test  (priority = 24)
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
	
	@Test (priority = 25)
	public static void FinalCoreDocument() throws BiffException, IOException{
		   
		    Workbook wb = Workbook.getWorkbook(srcCNRegression);
		    String Record= wb.getSheet("CTReviewRecord").getCell(0,1).getContents();
		    driver.findElement(By.linkText("Concept Notes")).click();
			WebElement record=driver.findElement(By.linkText(Record));
		    driver.findElement(By.linkText("Manage Documents")).click();
		    driver.findElement(By.id("page:frm:rpt:0:j_id160")).click();
		  }
	
	
	
	//CT is able to view the Final CT Review button and click it for CN with status as "Submitted to Global Fund"
	
			@Test  (priority = 26)
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
			Thread.sleep(2000);
			HighlightElement.elementHighlight(record);
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
			@Test  (priority = 27)
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
				//Click on Final Translation Review button
				WebElement FinalTranslationBtn = driver.findElement(By.id("Final Translation Review"));
				Thread.sleep(5000);
				HighlightElement.elementHighlight(FinalTranslationBtn);
				Thread.sleep(5000);
				FinalTranslationBtn.click();
				//Click on Confirm button
				WebElement ConfirmBtn = driver.findElement(By.xpath("//span[2]/a/strong"));
				Thread.sleep(5000);
				HighlightElement.elementHighlight(ConfirmBtn );
				Thread.sleep(5000);
				ConfirmBtn.click();
			}

			@Test  (priority = 28)
			public static void CCMEditChkAfterCTReview() throws BiffException, IOException, Exception  {
					
				Workbook wb = Workbook.getWorkbook(srcCNRegression);
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
			}	
				@Test  (priority = 28)
				public static void IterationScenarios() throws BiffException, IOException, Exception  {

				Workbook wb = Workbook.getWorkbook(srcCNRegression);
				baseUrl3 =  wb.getSheet("A2FLogindetails").getCell(2,1).getContents();
				driver.get(baseUrl3);
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
				DecisiononConceptNote = wb.getSheet("A2FDetails").getCell(0,i).getContents();
			    GACConfirmationofTRPDecision = wb.getSheet("A2FDetails").getCell(1,i).getContents();
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
					}
				}

			//Verify that the CCM is able to edit the CN having status as Iteration-not yet Submitted
					@Test  (priority = 29)
					public static void ItrNotSubmittedEditChk() throws BiffException, IOException, Exception  {

						Workbook wb = Workbook.getWorkbook(srcCNRegression);
						driver.get(baseUrl1);
						 int rowcount = wb.getSheet("Login").getRows();
						/*	
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
							}
							*/
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
					@Test  (priority = 30)
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

					
}

}
	
	

