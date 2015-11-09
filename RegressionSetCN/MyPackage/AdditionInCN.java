package MyPackage;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdditionInCN extends CNBase{
	
	 @Test (priority = 0)
	  public void AddNewPr() throws InterruptedException, Exception, Throwable {
		  	
	      Workbook wb = Workbook.getWorkbook(srcCNRegression);
	      Record = wb.getSheet("Record").getCell(0,1).getContents();
	      Thread.sleep(15000);
		  driver.findElement(By.linkText(Record)).click();
		  driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		  Thread.sleep(15000);
	      int rowcount = wb.getSheet("Principal Recipients").getRows();
	      /*
	     if(!driver.findElement(By.id("tsidLabel")).getText().equalsIgnoreCase("Concept Note and Grantmaking")) {
	 		 driver.findElement(By.id("tsidLabel")).click(); 
	 		 driver.findElement(By.linkText("Concept Note and Grantmaking")).click(); */  
	          for ( i=1;i<rowcount;++i){
	        	  String OrgnisationName =  wb.getSheet("Principal Recipients").getCell(0,i).getContents();
				  String OrgShortName = wb.getSheet("Principal Recipients").getCell(1, i).getContents();
				  
				  WebElement PRLink= driver.findElement(By.linkText("PRINCIPAL RECIPIENTS"));
				  HighlightElement.elementHighlight(PRLink);
				  PRLink.click();
				  
				  driver.findElement(By.linkText("Add new PR")).click();
				  
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
			  
			  WebElement ShortName = driver.findElement(By.id("j_id0:frm:pb:pbs:vLName"));
			  ShortName.sendKeys(OrgShortName);
			  WebElement selectPRType=driver.findElement(By.id("j_id0:frm:pb:pbs:prType"));
			  
			  Select PR0=new Select(selectPRType);
			  PR0.selectByIndex(2);
			  
			  driver.manage().timeouts().implicitlyWait(700, TimeUnit.SECONDS);
			  
			  WebElement selectPRSubType=driver.findElement(By.id("j_id0:frm:pb:pbs:prSubType"));
			  
			  Select PR2=new Select(selectPRSubType);
			  
			  PR2.selectByIndex(2);
			  
			  
			  
			  WebElement Save=  driver.findElement(By.id("j_id0:frm:pb:j_id5:bottom:j_id6"));
			  HighlightElement.elementHighlight(Save);
			  
			  Save.click();
			  
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
	       

	  @Test (priority = 1)
	  public void AddModule() throws Exception {
		  
		     Workbook wb = Workbook.getWorkbook(srcCNRegression);
		     driver.get(baseUrl1);
			   /* int rowcount = wb.getSheet("Login").getRows();
				
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
					Thread.sleep(5000); */
		     Record = wb.getSheet("Record").getCell(0,1).getContents();
		     driver.findElement(By.linkText(Record)).click();
		     Thread.sleep(5000);
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
	    
	   

	  @Test  (priority =  3)
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

	  @Test (priority = 4)
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
	  
	 
	 

	//Add Module Budget to the CN with status as not yet submitted
	 @Test  (priority = 5)
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
		
		//Click on Enter description button
		WebElement enter =driver.findElement(By.id("page:frm:rptrCNI:0:btnEnterDesc"));
		
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
		
		ok2.click();
		//Click on save button
		WebElement save =driver.findElement(By.id("page:frm:btnSaveDraft"));
		HighlightElement.elementHighlight(save);
		
		save.click();

		}


		
		@Test  (priority = 6)
		
		public void UploadFile() throws BiffException, IOException, InterruptedException {
			
			int j;
			int k;
			int l;
			int m;
			
			Workbook wb = Workbook.getWorkbook(srcCNRegression);
			int rowcount = wb.getSheet("ManageDocument").getRows();
			Record = wb.getSheet("Record").getCell(0,1).getContents();
			Thread.sleep(5000); 
			RecordLink = driver.findElement(By.linkText(Record));
			HighlightElement.elementHighlight(RecordLink);
			Thread.sleep(5000); 
			RecordLink.click();
			
			driver.findElement(By.linkText("MANAGE DOCUMENTS")).click();
			
			for (i=1; i<rowcount; ++i) {
					j = k = l = m = i;
					String FileType = wb.getSheet("ManageDocument").getCell(0, i).getContents();
					String Language = wb.getSheet("ManageDocument").getCell(1, j).getContents();
					String FilePath = wb.getSheet("ManageDocument").getCell(3, k).getContents();
					String Section = wb.getSheet("ManageDocument").getCell(4, l).getContents();
					String Description =  wb.getSheet("ManageDocument").getCell(5, m).getContents();
					Thread.sleep(5000);
					driver.findElement(By.xpath("//td[2]/a/strong")).click();
	                Thread.sleep(5000);
					WebElement SelectType = driver.findElement(By.id("page:frm:ifType"));
					SelectType.click();
					Select TypePick = new Select(SelectType);
					TypePick.selectByValue(FileType);
					
				    //new Select(driver.findElement(By.id("page:frm:ifType"))).selectByVisibleText(FileType);
				    
				    new Select(driver.findElement(By.id("page:frm:ifLanguage"))).selectByVisibleText(Language);
				    driver.findElement(By.id("page:frm:iSection")).clear();
				    
				    driver.findElement(By.id("page:frm:iSection")).sendKeys(Section);
				    
				    driver.findElement(By.id("page:frm:ipFile"));
				    
				    //driver.findElement(By.id("page:frm:ipFile")).clear();
				    driver.findElement(By.id("page:frm:ipFile")).sendKeys(FilePath);
				    
				    driver.findElement(By.id("page:frm:ifDescription")).click();
				    driver.findElement(By.id("page:frm:ifDescription")).click();
				    driver.findElement(By.id("page:frm:ifDescription")).clear();
				    driver.findElement(By.id("page:frm:ifDescription")).sendKeys(Description);
				    driver.findElement(By.cssSelector("strong")).click();
				    //driver.findElement(By.name("page:frm:j_id20")).click();
				  }
		}
		
}
