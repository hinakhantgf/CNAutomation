package MyPackage;

import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Delete extends CNBase {
	
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

}
